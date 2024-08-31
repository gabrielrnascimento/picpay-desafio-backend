package com.gabrielrnascimento.picpaydesafiobackend.infra.gateways.customer;

import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Customer;
import com.gabrielrnascimento.picpaydesafiobackend.infra.persistence.customer.CustomerEntity;


public class CustomerEntityMapper {

    public CustomerEntity toEntity(Customer customer) {
        return new CustomerEntity(customer.getId(), customer.getName(), customer.getEmail(), customer.getPassword(), customer.getDocument());
    }

    public Customer toDomain(CustomerEntity customerEntity) {
        return new Customer(customerEntity.getId(), customerEntity.getName(), customerEntity.getEmail(), customerEntity.getPassword(), customerEntity.getDocument());
    }
}
