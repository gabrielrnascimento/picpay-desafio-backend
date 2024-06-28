package com.gabrielrnascimento.picpaydesafiobackend.application.mocks.gateways;

import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.IAuthorizationGateway;


public class AuthorizationGatewayStub implements IAuthorizationGateway {

    final boolean response;

    public AuthorizationGatewayStub(boolean response) {
        this.response = response;
    }

    @Override
    public boolean isAuthorized() {
        return response;
    }
}
