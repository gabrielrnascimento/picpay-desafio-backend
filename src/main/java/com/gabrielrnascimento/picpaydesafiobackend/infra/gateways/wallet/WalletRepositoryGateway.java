package com.gabrielrnascimento.picpaydesafiobackend.infra.gateways.wallet;

import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.IWalletGateway;
import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;
import com.gabrielrnascimento.picpaydesafiobackend.infra.persistence.wallet.WalletEntity;
import com.gabrielrnascimento.picpaydesafiobackend.infra.persistence.wallet.WalletRepository;

import java.util.Optional;
import java.util.UUID;


public class WalletRepositoryGateway implements IWalletGateway {

    private final WalletRepository walletRepository;
    private final WalletEntityMapper walletEntityMapper;

    public WalletRepositoryGateway(WalletRepository walletRepository, WalletEntityMapper walletEntityMapper) {
        this.walletRepository = walletRepository;
        this.walletEntityMapper = walletEntityMapper;
    }

    @Override
    public Optional<Wallet> findByUserId(UUID walledId) {
        var walletEntity = walletRepository.findByUserId(walledId);
        var wallet = walletEntityMapper.toDomain(walletEntity);
        return Optional.of(wallet);
    }

    @Override
    public void save(Wallet wallet) {
        WalletEntity walletEntity = walletEntityMapper.toEntity(wallet);
        walletRepository.save(walletEntity);
    }
}
