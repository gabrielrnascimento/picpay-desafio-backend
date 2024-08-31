package com.gabrielrnascimento.picpaydesafiobackend.infra.persistence.customer;

import org.springframework.data.repository.Repository;

import java.util.UUID;


public interface CustomerRepository extends Repository<CustomerEntity, UUID> {

    void save(CustomerEntity customerEntity);

    CustomerEntity findById(UUID id);
}

