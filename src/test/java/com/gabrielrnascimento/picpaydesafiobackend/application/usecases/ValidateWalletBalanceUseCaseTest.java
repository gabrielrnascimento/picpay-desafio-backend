package com.gabrielrnascimento.picpaydesafiobackend.application.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ValidateWalletBalanceUseCaseTest {

    final Wallet wallet = new Wallet(UUID.randomUUID(), new BigDecimal(100), UUID.randomUUID());
    ValidateWalletBalanceUseCase sut = new ValidateWalletBalanceUseCase();

    @Test
    void shouldReturnFalseIfBalanceIsLowerThanValue() {
        BigDecimal value = new BigDecimal(200);
        boolean result = sut.hasEnoughBalance(wallet, value);

        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfBalanceIsGreaterOrEqualToValue() {
        BigDecimal value = new BigDecimal(50);
        boolean result = sut.hasEnoughBalance(wallet, value);

        assertTrue(result);
    }
}
