package com.gabrielrnascimento.picpaydesafiobackend.application.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.IValidateWalletBalanceUseCase;

import java.math.BigDecimal;


public class ValidateWalletBalanceUseCase implements IValidateWalletBalanceUseCase {

    @Override
    public boolean hasEnoughBalance(Wallet wallet, BigDecimal value) {
        return wallet.balance().compareTo(value) >= 0;
    }
}
