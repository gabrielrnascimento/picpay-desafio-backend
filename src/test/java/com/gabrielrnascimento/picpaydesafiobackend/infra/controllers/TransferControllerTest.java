package com.gabrielrnascimento.picpaydesafiobackend.infra.controllers;

import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;
import com.gabrielrnascimento.picpaydesafiobackend.domain.exceptions.InsufficientFundsException;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.ICreditWalletUseCase;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.IDebitWalletUseCase;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.IGetWalletUseCase;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.IValidatePayerIsCustomerUseCase;
import com.gabrielrnascimento.picpaydesafiobackend.infra.gateways.authorization.AuthorizationGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class TransferControllerTest {

    @Mock
    private AuthorizationGateway authorizationGateway;

    @Mock
    private IValidatePayerIsCustomerUseCase validatePayerIsCustomerUseCase;

    @Mock
    private IGetWalletUseCase getWalletUseCase;

    @Mock
    private ICreditWalletUseCase creditWalletUseCase;

    @Mock
    private IDebitWalletUseCase debitWalletUseCase;

    @InjectMocks
    private TransferController sut;

    private TransactionRequestDTO request;
    private Wallet payerWallet;
    private Wallet payeeWallet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new TransactionRequestDTO(new BigDecimal(10), UUID.randomUUID(), UUID.randomUUID());
        payerWallet = new Wallet(UUID.randomUUID(), new BigDecimal(100), UUID.randomUUID());
        payeeWallet = new Wallet(UUID.randomUUID(), new BigDecimal(100), UUID.randomUUID());
    }

    @Test
    void shouldReturnBadRequestWhenPayerIsNotACustomer() {
        when(getWalletUseCase.getWallet(request.payer())).thenReturn(payerWallet);
        when(getWalletUseCase.getWallet(request.payee())).thenReturn(payeeWallet);
        when(validatePayerIsCustomerUseCase.isCustomer(request.payer())).thenReturn(false);
        when(authorizationGateway.isAuthorized()).thenReturn(true);

        ResponseEntity<TransactionResponseDTO> response = sut.generateTransaction(request);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(response.getBody(), new TransactionResponseDTO("Payer is not a customer"));
    }

    @Test
    void shouldReturnConflictWhenPayerWalletHasInsufficientFunds() throws InsufficientFundsException {
        when(getWalletUseCase.getWallet(request.payer())).thenReturn(payerWallet);
        when(getWalletUseCase.getWallet(request.payee())).thenReturn(payeeWallet);
        when(validatePayerIsCustomerUseCase.isCustomer(request.payer())).thenReturn(true);
        when(authorizationGateway.isAuthorized()).thenReturn(true);

        doThrow(new InsufficientFundsException()).when(debitWalletUseCase).debit(payerWallet, request.value());
        ResponseEntity<TransactionResponseDTO> response = sut.generateTransaction(request);

        assertEquals(response.getStatusCode(), HttpStatus.CONFLICT);
        assertEquals(response.getBody(), new TransactionResponseDTO("Payer has insufficient funds"));
    }

    @Test
    void shouldCallWalletUseCasesCorrectlyAndReturnOkOnSuccess() throws InsufficientFundsException {
        when(getWalletUseCase.getWallet(request.payer())).thenReturn(payerWallet);
        when(getWalletUseCase.getWallet(request.payee())).thenReturn(payeeWallet);
        when(validatePayerIsCustomerUseCase.isCustomer(request.payer())).thenReturn(true);
        when(authorizationGateway.isAuthorized()).thenReturn(true);

        ResponseEntity<TransactionResponseDTO> response = sut.generateTransaction(request);

        verify(debitWalletUseCase).debit(payerWallet, request.value());
        verify(creditWalletUseCase).credit(payeeWallet, request.value());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), new TransactionResponseDTO("Transaction completed successfully"));

    }
}
