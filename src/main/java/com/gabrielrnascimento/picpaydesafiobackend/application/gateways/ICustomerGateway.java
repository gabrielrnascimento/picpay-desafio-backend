package com.gabrielrnascimento.picpaydesafiobackend.application.gateways;

import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Customer;

import java.util.Optional;
import java.util.UUID;


public interface ICustomerGateway {

    Optional<Customer> findById(UUID id);
}
