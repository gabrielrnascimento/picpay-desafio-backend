package com.gabrielrnascimento.picpaydesafiobackend.domain.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;

import java.util.UUID;


public interface IGetWalletUseCase {

    Wallet getWallet(UUID walledId);
}
