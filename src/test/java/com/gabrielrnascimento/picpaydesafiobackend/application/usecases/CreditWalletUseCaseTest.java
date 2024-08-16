package com.gabrielrnascimento.picpaydesafiobackend.application.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CreditWalletUseCaseTest {

    @Test
    void shouldIncreaseWalletAmount() {
        Wallet wallet = new Wallet(UUID.randomUUID(), new BigDecimal("100.00"), UUID.randomUUID());
        CreditWalletUseCase sut = new CreditWalletUseCase(wallet);

        sut.credit(new BigDecimal("10.00"));

        assertEquals(new BigDecimal("110.00"), wallet.balance());

    }

}
