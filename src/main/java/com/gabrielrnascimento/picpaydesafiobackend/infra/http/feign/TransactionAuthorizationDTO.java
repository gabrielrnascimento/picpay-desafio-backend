package com.gabrielrnascimento.picpaydesafiobackend.infra.http.feign;

import com.gabrielrnascimento.picpaydesafiobackend.infra.gateways.AuthorizationStatus;


public record TransactionAuthorizationDTO(String message) {

    public TransactionAuthorizationDTO(AuthorizationStatus status) {
        this(status.getMessage());
    }
}
