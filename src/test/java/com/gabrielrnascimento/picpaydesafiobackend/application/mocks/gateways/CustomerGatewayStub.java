package com.gabrielrnascimento.picpaydesafiobackend.application.mocks.gateways;

import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.ICustomerGateway;
import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Customer;

import java.util.Optional;
import java.util.UUID;


public class CustomerGatewayStub implements ICustomerGateway {

    final Customer response;

    public CustomerGatewayStub(Customer customer) {
        this.response = customer;
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        if (response == null) {
            return Optional.empty();
        }
        return Optional.of(response);
    }
}
