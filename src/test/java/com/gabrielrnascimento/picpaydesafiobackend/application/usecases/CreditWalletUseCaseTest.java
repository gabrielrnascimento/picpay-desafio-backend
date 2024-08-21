package com.gabrielrnascimento.picpaydesafiobackend.application.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.IWalletGateway;
import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class CreditWalletUseCaseTest {

    @Test
    void shouldIncreaseWalletAmount() {
        IWalletGateway walletGatewayStub = mock(IWalletGateway.class);
        Wallet wallet = new Wallet(UUID.randomUUID(), new BigDecimal("100.00"), UUID.randomUUID());
        CreditWalletUseCase sut = new CreditWalletUseCase(walletGatewayStub);

        sut.credit(wallet, new BigDecimal("10.00"));

        assertEquals(new BigDecimal("110.00"), wallet.balance());
        verify(walletGatewayStub).save(wallet);
    }

}
