package com.gabrielrnascimento.picpaydesafiobackend.domain.entities;

import com.gabrielrnascimento.picpaydesafiobackend.domain.enums.DocumentType;

import java.util.UUID;


public abstract class AbstractUser {

    private final DocumentType documentType;
    private final UUID id;
    private String name;
    private String email;
    private String password;
    private String document;

    public AbstractUser(UUID id, String name, String email, String password, DocumentType documentType, String document) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.documentType = documentType;
        this.document = document;
    }

    public AbstractUser(String name, String email, String password, DocumentType documentType, String document) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.documentType = documentType;
        this.document = document;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

}
