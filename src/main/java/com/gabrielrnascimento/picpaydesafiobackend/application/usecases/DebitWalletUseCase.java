package com.gabrielrnascimento.picpaydesafiobackend.application.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.IWalletGateway;
import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;
import com.gabrielrnascimento.picpaydesafiobackend.domain.exceptions.InsufficientFundsException;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.IDebitWalletUseCase;

import java.math.BigDecimal;


public class DebitWalletUseCase implements IDebitWalletUseCase {

    private final IWalletGateway walletGateway;

    public DebitWalletUseCase(IWalletGateway walletGateway) {
        this.walletGateway = walletGateway;
    }

    @Override
    public void debit(Wallet wallet, BigDecimal amount) throws InsufficientFundsException {
        BigDecimal newBalance = wallet.balance().subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientFundsException();
        }
        wallet.setBalance(newBalance);
        walletGateway.save(wallet);
    }
}
