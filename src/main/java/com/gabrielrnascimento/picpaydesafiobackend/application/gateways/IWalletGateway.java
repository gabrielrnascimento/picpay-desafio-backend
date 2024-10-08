package com.gabrielrnascimento.picpaydesafiobackend.application.gateways;

import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;

import java.util.Optional;
import java.util.UUID;


public interface IWalletGateway {

    Optional<Wallet> findByUserId(UUID walledId);

    void save(Wallet wallet);
}
