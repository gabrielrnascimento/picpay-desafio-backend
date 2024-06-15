package com.gabrielrnascimento.picpaydesafiobackend.application.usecases;

import com.gabrielrnascimento.picpaydesafiobackend.application.mocks.gateways.AuthorizationGatewayStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ValidateTransactionAuthorizationUseCaseTest {

    ValidateTransactionAuthorizationUseCase makeSut(boolean isAuthorized) {
        AuthorizationGatewayStub authorizationGatewayStub = new AuthorizationGatewayStub(isAuthorized);
        return new ValidateTransactionAuthorizationUseCase(authorizationGatewayStub);
    }

    @Test
    void shouldReturnFalseIfTransactionIsUnauthorized() {
        ValidateTransactionAuthorizationUseCase sut = makeSut(false);

        boolean result = sut.isAuthorized();

        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfTransactionIsAuthorized() {
        ValidateTransactionAuthorizationUseCase sut = makeSut(true);

        boolean result = sut.isAuthorized();

        assertTrue(result);
    }
}
