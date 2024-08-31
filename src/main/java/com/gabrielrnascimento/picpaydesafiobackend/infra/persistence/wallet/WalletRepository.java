package com.gabrielrnascimento.picpaydesafiobackend.infra.persistence.wallet;

import org.springframework.data.repository.Repository;

import java.util.UUID;


public interface WalletRepository extends Repository<WalletEntity, UUID> {

    void save(WalletEntity walletEntity);

    WalletEntity findById(UUID id);

    WalletEntity findByUserId(UUID userId);
}
