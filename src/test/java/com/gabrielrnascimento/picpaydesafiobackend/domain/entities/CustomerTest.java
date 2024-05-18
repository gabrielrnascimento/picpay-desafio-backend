package com.gabrielrnascimento.picpaydesafiobackend.domain.entities;

import com.gabrielrnascimento.picpaydesafiobackend.domain.enums.DocumentType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


class CustomerTest {

    final UUID id = UUID.randomUUID();
    final String name = "Any Name";
    final String email = "Any Email";
    final String password = "Any Password";
    final String document = "Any Document";
    final Customer sut = new Customer(id, name, email, password, document);

    @Test
    void shouldInheritFromAbstractUser() {
        assertInstanceOf(AbstractUser.class, sut);
    }

    @Test
    void shouldSetDocumentTypeAsCPF() {
        assertEquals(DocumentType.CPF, sut.getDocumentType());
    }
}

