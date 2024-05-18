package com.gabrielrnascimento.picpaydesafiobackend.domain.entities;

import com.gabrielrnascimento.picpaydesafiobackend.domain.enums.DocumentType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class UserTestImpl extends AbstractUser {

    public UserTestImpl(UUID id, String name, String email, String password, DocumentType documentType, String document) {
        super(id, name, email, password, documentType, document);
    }

    public UserTestImpl(String name, String email, String password, DocumentType documentType, String document) {
        super(name, email, password, documentType, document);
    }
}

class AbstractUserTest {

    final UUID id = UUID.randomUUID();
    final String name = "Any Name";
    final String email = "Any Email";
    final String password = "Any Password";
    final DocumentType documentType = DocumentType.CPF;
    final String document = "Any Document";

    UserTestImpl sut = new UserTestImpl(id, name, email, password, documentType, document);

    @Test
    void shouldCreateAbstractUserWithCorrectProperties() {
        assertNotNull(sut);
        assertEquals(id, sut.getId());
        assertEquals(name, sut.getName());
        assertEquals(email, sut.getEmail());
        assertEquals(password, sut.getPassword());
        assertEquals(documentType, sut.getDocumentType());
        assertEquals(document, sut.getDocument());
    }

    @Test
    void shouldCreateAbstractUserWithRandomId() {
        UserTestImpl sut = new UserTestImpl(name, email, password, documentType, document);
        assertNotNull(sut.getId());
    }

    @Test
    void shouldUpdateAbstractUser() {
        String newName = "New Name";
        String newEmail = "New Email";
        String newPassword = "New Password";
        String newDocument = "New Document";

        sut.setName(newName);
        sut.setEmail(newEmail);
        sut.setPassword(newPassword);
        sut.setDocument(newDocument);

        assertEquals(newName, sut.getName());
        assertEquals(newEmail, sut.getEmail());
        assertEquals(newPassword, sut.getPassword());
        assertEquals(newDocument, sut.getDocument());
    }
}
