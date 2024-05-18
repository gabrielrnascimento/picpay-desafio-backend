package com.gabrielrnascimento.picpaydesafiobackend.application.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.ICustomerGateway;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.IValidatePayerIsCustomerUseCase;

import java.util.UUID;


public class ValidatePayerIsCustomerUseCase implements IValidatePayerIsCustomerUseCase {

    private final ICustomerGateway customerGateway;

    public ValidatePayerIsCustomerUseCase(ICustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    @Override
    public boolean isCustomer(UUID payerId) {
        return customerGateway.findById(payerId).isPresent();
    }
}
