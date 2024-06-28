package com.gabrielrnascimento.picpaydesafiobackend.application.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.IAuthorizationGateway;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.IValidateTransactionAuthorizationUseCase;


public class ValidateTransactionAuthorizationUseCase implements IValidateTransactionAuthorizationUseCase {

    private final IAuthorizationGateway authorizationGateway;

    public ValidateTransactionAuthorizationUseCase(IAuthorizationGateway authorizationGateway) {
        this.authorizationGateway = authorizationGateway;
    }

    @Override
    public boolean isAuthorized() {
        return authorizationGateway.isAuthorized();
    }
}
