package com.gabrielrnascimento.picpaydesafiobackend.domain.entities;

import com.gabrielrnascimento.picpaydesafiobackend.domain.enums.DocumentType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


class ShopkeeperTest {

    final UUID id = UUID.randomUUID();
    final String name = "Any Name";
    final String email = "Any Email";
    final String password = "Any Password";
    final String document = "Any Document";
    final Shopkeeper sut = new Shopkeeper(id, name, email, password, document);

    @Test
    void shouldInheritFromAbstractUser() {
        assertInstanceOf(AbstractUser.class, sut);
    }

    @Test
    void shouldSetDocumentTypeAsCNPJ() {
        assertEquals(DocumentType.CNPJ, sut.getDocumentType());
    }

    @Test
    void shouldCreateShopkeeperIfIdIsNotProvided() {
        var shopkeeper = new Shopkeeper(name, email, password, document);
        assertEquals(name, shopkeeper.getName());
        assertEquals(email, shopkeeper.getEmail());
        assertEquals(password, shopkeeper.getPassword());
        assertEquals(DocumentType.CNPJ, shopkeeper.getDocumentType());
        assertEquals(document, shopkeeper.getDocument());
    }
}

