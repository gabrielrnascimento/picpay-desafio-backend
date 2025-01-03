package com.gabrielrnascimento.picpaydesafiobackend.application.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.IWalletGateway;
import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;
import com.gabrielrnascimento.picpaydesafiobackend.domain.exceptions.NotFoundException;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.IGetWalletUseCase;

import java.util.UUID;


public class GetWalletUseCase implements IGetWalletUseCase {

    private final IWalletGateway walletGateway;

    public GetWalletUseCase(IWalletGateway walletGateway) {
        this.walletGateway = walletGateway;
    }

    @Override
    public Wallet getWallet(UUID userId) {
        try {
            return walletGateway.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Wallet"));
        } catch (NotFoundException e) {
            return null;
        }
    }
}
