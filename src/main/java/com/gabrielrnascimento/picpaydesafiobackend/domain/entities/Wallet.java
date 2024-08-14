package com.gabrielrnascimento.picpaydesafiobackend.domain.entities;

import java.math.BigDecimal;
import java.util.UUID;


public class Wallet {

    private final UUID id;
    private final UUID userId;
    private BigDecimal balance;

    public Wallet(UUID id, BigDecimal balance, UUID userId) {
        this.id = id;
        this.balance = balance;
        this.userId = userId;
    }

    public BigDecimal balance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public UUID id() {
        return id;
    }

    public UUID userId() {
        return userId;
    }
}
