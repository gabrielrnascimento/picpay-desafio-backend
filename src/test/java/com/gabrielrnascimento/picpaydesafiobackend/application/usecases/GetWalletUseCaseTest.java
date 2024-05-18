package com.gabrielrnascimento.picpaydesafiobackend.application.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.IWalletGateway;
import com.gabrielrnascimento.picpaydesafiobackend.application.mocks.gateways.WalletGatewayStub;
import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class GetWalletUseCaseTest {

    final Wallet wallet = new Wallet(UUID.randomUUID(), new BigDecimal("100.00"), UUID.randomUUID());

    GetWalletUseCase makeSut(Wallet wallet) {
        IWalletGateway walletGateway = new WalletGatewayStub(wallet);
        return new GetWalletUseCase(walletGateway);
    }

    @Test
    void shouldReturnNullIfNoWalletIsFound() {
        UUID walletId = UUID.randomUUID();
        GetWalletUseCase sut = makeSut(null);

        Wallet result = sut.getWallet(walletId);

        assertNull(result);
    }

    @Test
    void shouldReturnWalletIfFound() {
        GetWalletUseCase sut = makeSut(wallet);

        Wallet result = sut.getWallet(wallet.id());

        assertEquals(result, wallet);
    }
}
