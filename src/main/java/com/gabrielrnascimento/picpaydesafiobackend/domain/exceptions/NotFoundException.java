package com.gabrielrnascimento.picpaydesafiobackend.domain.exceptions;

public class NotFoundException extends Exception {

    public NotFoundException(String message) {
        super(message + " not found");
    }
}
