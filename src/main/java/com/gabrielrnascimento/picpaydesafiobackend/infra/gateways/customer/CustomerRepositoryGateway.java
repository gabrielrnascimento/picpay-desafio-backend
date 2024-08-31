package com.gabrielrnascimento.picpaydesafiobackend.infra.gateways.customer;

import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.ICustomerGateway;
import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Customer;
import com.gabrielrnascimento.picpaydesafiobackend.infra.persistence.customer.CustomerRepository;

import java.util.Optional;
import java.util.UUID;


public class CustomerRepositoryGateway implements ICustomerGateway {

    private final CustomerRepository customerRepository;
    private final CustomerEntityMapper customerEntityMapper;

    public CustomerRepositoryGateway(CustomerRepository customerRepository, CustomerEntityMapper customerEntityMapper) {
        this.customerRepository = customerRepository;
        this.customerEntityMapper = customerEntityMapper;
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        var customerEntity = customerRepository.findById(id);
        var customer = customerEntityMapper.toDomain(customerEntity);
        return Optional.of(customer);
    }
}
