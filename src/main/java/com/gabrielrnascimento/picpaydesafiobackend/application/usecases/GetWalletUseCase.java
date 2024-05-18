package com.gabrielrnascimento.picpaydesafiobackend.application.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.IWalletGateway;
import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.IGetWalletUseCase;

import java.util.Optional;
import java.util.UUID;


public class GetWalletUseCase implements IGetWalletUseCase {

    private final IWalletGateway walletGateway;

    public GetWalletUseCase(IWalletGateway walletGateway) {
        this.walletGateway = walletGateway;
    }

    @Override
    public Wallet getWallet(UUID walledId) {
        Optional<Wallet> result = walletGateway.findById(walledId);
        return result.orElse(null);
    }
}
