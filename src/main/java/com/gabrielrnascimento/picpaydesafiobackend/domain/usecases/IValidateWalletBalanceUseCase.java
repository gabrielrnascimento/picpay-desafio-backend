package com.gabrielrnascimento.picpaydesafiobackend.domain.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;

import java.math.BigDecimal;


public interface IValidateWalletBalanceUseCase {

    boolean hasEnoughBalance(Wallet wallet, BigDecimal value);
}
