package com.gabrielrnascimento.picpaydesafiobackend.application.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.ICreditWalletUseCase;

import java.math.BigDecimal;


public class CreditWalletUseCase implements ICreditWalletUseCase {

    private final Wallet wallet;

    public CreditWalletUseCase(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public void credit(BigDecimal amount) {
        wallet.setBalance(wallet.balance().add(amount));
    }

}
