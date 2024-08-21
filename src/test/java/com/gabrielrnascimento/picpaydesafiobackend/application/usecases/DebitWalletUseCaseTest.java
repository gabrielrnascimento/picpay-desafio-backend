package com.gabrielrnascimento.picpaydesafiobackend.application.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.IWalletGateway;
import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;
import com.gabrielrnascimento.picpaydesafiobackend.domain.exceptions.InsufficientFundsException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class DebitWalletUseCaseTest {

    @Test
    void shouldDecreaseWalletAmount() throws InsufficientFundsException {
        IWalletGateway walletGatewayStub = mock(IWalletGateway.class);
        Wallet wallet = new Wallet(UUID.randomUUID(), new BigDecimal("100.00"), UUID.randomUUID());
        DebitWalletUseCase sut = new DebitWalletUseCase(walletGatewayStub);

        sut.debit(wallet, new BigDecimal("10.00"));

        assertEquals(new BigDecimal("90.00"), wallet.balance());
        verify(walletGatewayStub).save(wallet);

    }

    @Test
    void shouldThrowInsufficientFundsExceptionWhenDebitAmountIsGreaterThanWalletBalance() {
        IWalletGateway walletGatewayStub = mock(IWalletGateway.class);
        Wallet wallet = new Wallet(UUID.randomUUID(), new BigDecimal("100.00"), UUID.randomUUID());
        DebitWalletUseCase sut = new DebitWalletUseCase(walletGatewayStub);

        assertThrows(InsufficientFundsException.class, () -> sut.debit(wallet, new BigDecimal("110.00")));
    }
}
