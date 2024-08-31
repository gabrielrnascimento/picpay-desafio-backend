package com.gabrielrnascimento.picpaydesafiobackend.infra.gateways.wallet;

import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Wallet;
import com.gabrielrnascimento.picpaydesafiobackend.infra.persistence.customer.CustomerRepository;
import com.gabrielrnascimento.picpaydesafiobackend.infra.persistence.wallet.WalletEntity;


public class WalletEntityMapper {

    private final CustomerRepository customerRepository;

    public WalletEntityMapper(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public WalletEntity toEntity(Wallet wallet) {
        var customerEntity = customerRepository.findById(wallet.userId());
        return new WalletEntity(wallet.id(), customerEntity, wallet.balance());
    }

    public Wallet toDomain(WalletEntity walletEntity) {
        return new Wallet(walletEntity.getId(), walletEntity.getBalance(), walletEntity.getCustomer().getId());
    }

}
