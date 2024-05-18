package com.gabrielrnascimento.picpaydesafiobackend.application.mocks.gateways;

import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.IWalletGateway;
import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;

import java.util.Optional;
import java.util.UUID;


public class WalletGatewayStub implements IWalletGateway {

    final Wallet response;

    public WalletGatewayStub(Wallet wallet) {
        this.response = wallet;
    }

    @Override
    public Optional<Wallet> findById(UUID id) {
        if (response == null) {
            return Optional.empty();
        }
        return Optional.of(response);
    }
}
