package com.gabrielrnascimento.picpaydesafiobackend.infra.gateways;

import com.gabrielrnascimento.picpaydesafiobackend.infra.http.feign.TransactionAuthorizationDTO;
import com.gabrielrnascimento.picpaydesafiobackend.infra.http.feign.TransactionAuthorizationFeignClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class AuthorizationGatewayTest {

    private final TransactionAuthorizationFeignClient transactionClientMock = mock(TransactionAuthorizationFeignClient.class);

    AuthorizationGateway makeSut(TransactionAuthorizationFeignClient transactionClient) {
        return new AuthorizationGateway(transactionClient);
    }

    @Test
    void shouldReturnTrueIfExternalServiceReturnsAuthorized() {
        AuthorizationGateway sut = makeSut(transactionClientMock);
        when(transactionClientMock.authorize()).thenReturn(new TransactionAuthorizationDTO(AuthorizationStatus.AUTHORIZED));

        boolean result = sut.isAuthorized();

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfExternalServiceReturnsUnauthorized() {
        AuthorizationGateway sut = makeSut(transactionClientMock);
        when(transactionClientMock.authorize()).thenReturn(new TransactionAuthorizationDTO(AuthorizationStatus.UNAUTHORIZED));

        boolean result = sut.isAuthorized();

        assertFalse(result);
    }

}
