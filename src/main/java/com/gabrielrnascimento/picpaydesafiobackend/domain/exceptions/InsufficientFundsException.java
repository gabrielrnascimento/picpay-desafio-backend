package com.gabrielrnascimento.picpaydesafiobackend.domain.exceptions;

public class InsufficientFundsException extends Exception {

    public InsufficientFundsException() {
        super("Insufficient funds");
    }

}
