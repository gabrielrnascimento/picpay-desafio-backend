package com.gabrielrnascimento.picpaydesafiobackend.application.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.IWalletGateway;
import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class GetWalletUseCaseTest {

    final Wallet wallet = new Wallet(UUID.randomUUID(), new BigDecimal("100.00"), UUID.randomUUID());
    final IWalletGateway walletGateway = mock(IWalletGateway.class);

    GetWalletUseCase makeSut(Wallet wallet) {
        return new GetWalletUseCase(walletGateway);
    }

    @Test
    void shouldReturnNullIfNoWalletIsFound() {
        when(walletGateway.findByUserId(wallet.id())).thenReturn(Optional.empty());
        UUID walletId = UUID.randomUUID();
        GetWalletUseCase sut = makeSut(null);

        Wallet result = sut.getWallet(walletId);

        assertNull(result);
    }

    @Test
    void shouldReturnWalletIfFound() {
        when(walletGateway.findByUserId(wallet.id())).thenReturn(Optional.of(wallet));
        GetWalletUseCase sut = makeSut(wallet);

        Wallet result = sut.getWallet(wallet.id());

        assertEquals(result, wallet);
    }
}
