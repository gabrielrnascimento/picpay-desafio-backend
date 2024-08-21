package com.gabrielrnascimento.picpaydesafiobackend.domain.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;
import com.gabrielrnascimento.picpaydesafiobackend.domain.exceptions.InsufficientFundsException;

import java.math.BigDecimal;


public interface IDebitWalletUseCase {

    void debit(Wallet wallet, BigDecimal amount) throws InsufficientFundsException;
}
