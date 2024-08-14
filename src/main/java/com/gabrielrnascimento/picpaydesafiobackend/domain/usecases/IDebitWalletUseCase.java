package com.gabrielrnascimento.picpaydesafiobackend.domain.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.domain.exceptions.InsufficientFundsException;

import java.math.BigDecimal;


public interface IDebitWalletUseCase {

    void debit(BigDecimal amount) throws InsufficientFundsException;
}
