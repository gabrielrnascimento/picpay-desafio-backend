package com.gabrielrnascimento.picpaydesafiobackend.application.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.IWalletGateway;
import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.ICreditWalletUseCase;

import java.math.BigDecimal;


public class CreditWalletUseCase implements ICreditWalletUseCase {

    private final IWalletGateway walletGateway;

    public CreditWalletUseCase(IWalletGateway walletGateway) {
        this.walletGateway = walletGateway;
    }

    @Override
    public void credit(Wallet wallet, BigDecimal amount) {
        wallet.setBalance(wallet.balance().add(amount));
        walletGateway.save(wallet);
    }

}
