package com.gabrielrnascimento.picpaydesafiobackend.application.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.ICustomerGateway;
import com.gabrielrnascimento.picpaydesafiobackend.application.mocks.gateways.CustomerGatewayStub;
import com.gabrielrnascimento.picpaydesafiobackend.domain.entities.Customer;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ValidatePayerIsCustomerUseCaseTest {

    private final UUID payerId = UUID.randomUUID();
    private final Customer customer = new Customer(payerId, "Any Name", "Any Email", "Any Password", "Any Document");

    ValidatePayerIsCustomerUseCase makeSut(Customer customer) {
        ICustomerGateway customerGateway = new CustomerGatewayStub(customer);
        return new ValidatePayerIsCustomerUseCase(customerGateway);
    }

    @Test
    void shouldReturnFalseIfPayerIsNotCustomer() {
        ValidatePayerIsCustomerUseCase sut = makeSut(null);

        boolean result = sut.isCustomer(payerId);

        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfPayerIsCustomer() {
        ValidatePayerIsCustomerUseCase sut = makeSut(customer);

        boolean result = sut.isCustomer(payerId);

        assertTrue(result);
    }
}
