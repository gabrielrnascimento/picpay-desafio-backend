package com.gabrielrnascimento.picpaydesafiobackend.infra.database;

import com.gabrielrnascimento.picpaydesafiobackend.infra.persistence.customer.CustomerEntity;
import com.gabrielrnascimento.picpaydesafiobackend.infra.persistence.customer.CustomerRepository;
import com.gabrielrnascimento.picpaydesafiobackend.infra.persistence.wallet.WalletEntity;
import com.gabrielrnascimento.picpaydesafiobackend.infra.persistence.wallet.WalletRepository;
import org.springframework.boot.test.context.TestComponent;

import java.math.BigDecimal;
import java.util.UUID;


@TestComponent
public class DatabaseHelper {

    private final CustomerRepository customerRepository;
    private final WalletRepository walletRepository;

    public UUID payerId;
    public UUID payeeId;

    public DatabaseHelper(CustomerRepository customerRepository, WalletRepository walletRepository) {
        this.customerRepository = customerRepository;
        this.walletRepository = walletRepository;
    }

    public void seed() {
        CustomerEntity payer = seedCustomer("Payer", "payer@mail.com", "12345678901");
        payerId = payer.getId();
        WalletEntity payerWallet = seedWallet(payer, new BigDecimal("1000.00"));

        CustomerEntity payee = seedCustomer("Payee", "payee@mail.com", "12345678902");
        payeeId = payee.getId();
        WalletEntity payeeWallet = seedWallet(payee, new BigDecimal("500.00"));

        System.out.println("Payer: " + payer);
        System.out.println("Payer Wallet: " + payerWallet);
        System.out.println("Payee: " + payee);
        System.out.println("Payee Wallet: " + payeeWallet);
    }

    private CustomerEntity seedCustomer(String name, String email, String document) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(name);
        customerEntity.setEmail(email);
        customerEntity.setDocument(document);
        customerRepository.save(customerEntity);
        return customerRepository.findById(customerEntity.getId());
    }

    private WalletEntity seedWallet(CustomerEntity customer, BigDecimal balance) {
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setCustomer(customer);
        walletEntity.setBalance(balance);
        walletRepository.save(walletEntity);
        return walletRepository.findById(walletEntity.getId());
    }

}
