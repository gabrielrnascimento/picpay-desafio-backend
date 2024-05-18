package com.gabrielrnascimento.picpaydesafiobackend.domain.entities;

import com.gabrielrnascimento.picpaydesafiobackend.domain.enums.DocumentType;

import java.util.UUID;


public class Shopkeeper extends AbstractUser {

    public Shopkeeper(UUID id, String name, String email, String password, String document) {
        super(id, name, email, password, DocumentType.CNPJ, document);
    }

    public Shopkeeper(String name, String email, String password, String document) {
        super(name, email, password, DocumentType.CNPJ, document);
    }
}
