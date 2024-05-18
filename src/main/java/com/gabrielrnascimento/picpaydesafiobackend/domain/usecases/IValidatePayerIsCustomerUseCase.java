package com.gabrielrnascimento.picpaydesafiobackend.domain.usecases;

import java.util.UUID;


public interface IValidatePayerIsCustomerUseCase {

    boolean isCustomer(UUID payerId);
}
