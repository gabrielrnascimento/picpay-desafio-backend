package com.gabrielrnascimento.picpaydesafiobackend.domain.entities;

import com.gabrielrnascimento.picpaydesafiobackend.domain.enums.DocumentType;

import java.util.UUID;


public class Customer extends AbstractUser {

    public Customer(UUID id, String name, String email, String password, String document) {
        super(id, name, email, password, DocumentType.CPF, document);
    }

    public Customer(String name, String email, String password, String document) {
        super(name, email, password, DocumentType.CPF, document);
    }
}
