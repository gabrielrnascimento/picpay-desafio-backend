package com.gabrielrnascimento.picpaydesafiobackend.application.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;
import com.gabrielrnascimento.picpaydesafiobackend.domain.exceptions.InsufficientFundsException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


class DebitWalletUseCaseTest {

    @Test
    void shouldDecreaseWalletAmount() throws InsufficientFundsException {
        Wallet wallet = new Wallet(UUID.randomUUID(), new BigDecimal("100.00"), UUID.randomUUID());
        DebitWalletUseCase sut = new DebitWalletUseCase(wallet);

        sut.debit(new BigDecimal("10.00"));

        assertEquals(new BigDecimal("90.00"), wallet.balance());
    }

    @Test
    void shouldThrowInsufficientFundsExceptionWhenDebitAmountIsGreaterThanWalletBalance() {
        Wallet wallet = new Wallet(UUID.randomUUID(), new BigDecimal("100.00"), UUID.randomUUID());
        DebitWalletUseCase sut = new DebitWalletUseCase(wallet);

        assertThrows(InsufficientFundsException.class, () -> sut.debit(new BigDecimal("110.00")));
    }
}
