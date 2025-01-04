package com.gabrielrnascimento.picpaydesafiobackend;

import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.IAuthorizationGateway;
import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.INotificationGateway;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.ICreditWalletUseCase;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.IDebitWalletUseCase;
import com.gabrielrnascimento.picpaydesafiobackend.infra.database.DatabaseHelper;
import com.gabrielrnascimento.picpaydesafiobackend.infra.gateways.authorization.AuthorizationStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.TestcontainersExtension;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {
        "spring.config.import=optional:secrets.properties",
    }
)
@Testcontainers
@AutoConfigureMockMvc
@ExtendWith(TestcontainersExtension.class)
@ComponentScan(basePackages = "com.gabrielrnascimento.picpaydesafiobackend.infra.database")
public class TransferIntegrationTest {

    public static final DockerImageName MOCKSERVER_IMAGE = DockerImageName
        .parse("mockserver/mockserver")
        .withTag("mockserver-" + MockServerClient.class.getPackage().getImplementationVersion());

    @Container
    public static MockServerContainer mockServer = new MockServerContainer(MOCKSERVER_IMAGE);

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"))
        .withDatabaseName("picpay-desafio-backend")
        .withUsername("admin")
        .withPassword("admin");

    @Container
    public static KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.4.0"));

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DatabaseHelper databaseHelper;

    @MockBean
    private IAuthorizationGateway authorizationGateway;

    @MockBean
    private IDebitWalletUseCase debitWalletUseCase;

    @MockBean
    private ICreditWalletUseCase creditWalletUseCase;

    @MockBean
    private INotificationGateway notificationGateway;

    @BeforeAll
    public static void setup() {
        System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());
        System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
        System.setProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        System.setProperty("transaction.authorization.url", mockServer.getHost() + ":" + mockServer.getServerPort());
        System.setProperty("spring.kafka.bootstrap-servers", kafkaContainer.getBootstrapServers());
    }

    @BeforeEach
    public void seedDatabase() {
        databaseHelper.seed();
    }

    @Test
    public void shouldInvokeDependenciesWhenTransactionIsAuthorizedSuccessfully() throws Exception {
        when(authorizationGateway.isAuthorized()).thenReturn(true);
        try (MockServerClient mockServerClient = new MockServerClient(mockServer.getHost(), mockServer.getServerPort())) {
            Map<String, String> mockResponse = Map.of("message", AuthorizationStatus.AUTHORIZED.getMessage());
            JSONObject mockResponseJson = new JSONObject(mockResponse.toString());
            mockServerClient
                .when(org.mockserver.model.HttpRequest.request())
                .respond(org.mockserver.model.HttpResponse.response()
                    .withHeader("Content-Type", "application/json")
                    .withBody(mockResponseJson.toString()));

            mockMvc.perform(
                    post("/transfer")
                        .contentType("application/json")
                        .content(String.format("""
                            {
                                "value": 100.0,
                                "payer": "%s",
                                "payee": "%s"
                                }
                            """, databaseHelper.payerId, databaseHelper.payeeId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Transaction completed successfully"));

            verify(authorizationGateway, times(1)).isAuthorized();
            verify(debitWalletUseCase, times(1)).debit(any(), eq(BigDecimal.valueOf(100.0)));
            verify(creditWalletUseCase, times(1)).credit(any(), eq(BigDecimal.valueOf(100.0)));
            verify(notificationGateway, times(1)).notifyTransactionComplete(any());
        }
    }
}
