package com.gabrielrnascimento.picpaydesafiobackend.infra.persistence.wallet;

import com.gabrielrnascimento.picpaydesafiobackend.infra.persistence.customer.CustomerEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;


@Entity(name = "wallet")
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private CustomerEntity user;
    private BigDecimal balance;

    public WalletEntity() {
    }

    public WalletEntity(UUID id, CustomerEntity user, BigDecimal balance) {
        this.id = id;
        this.user = user;
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CustomerEntity getCustomer() {
        return user;
    }

    public void setCustomer(CustomerEntity customer) {
        this.user = customer;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
