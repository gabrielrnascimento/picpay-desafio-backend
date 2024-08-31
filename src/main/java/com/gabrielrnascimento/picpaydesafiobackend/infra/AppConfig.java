package com.gabrielrnascimento.picpaydesafiobackend.infra;

import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.ICustomerGateway;
import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.IWalletGateway;
import com.gabrielrnascimento.picpaydesafiobackend.application.usecases.CreditWalletUseCase;
import com.gabrielrnascimento.picpaydesafiobackend.application.usecases.DebitWalletUseCase;
import com.gabrielrnascimento.picpaydesafiobackend.application.usecases.GetWalletUseCase;
import com.gabrielrnascimento.picpaydesafiobackend.application.usecases.ValidatePayerIsCustomerUseCase;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.ICreditWalletUseCase;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.IDebitWalletUseCase;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.IGetWalletUseCase;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.IValidatePayerIsCustomerUseCase;
import com.gabrielrnascimento.picpaydesafiobackend.infra.gateways.customer.CustomerEntityMapper;
import com.gabrielrnascimento.picpaydesafiobackend.infra.gateways.customer.CustomerRepositoryGateway;
import com.gabrielrnascimento.picpaydesafiobackend.infra.gateways.wallet.WalletEntityMapper;
import com.gabrielrnascimento.picpaydesafiobackend.infra.gateways.wallet.WalletRepositoryGateway;
import com.gabrielrnascimento.picpaydesafiobackend.infra.persistence.customer.CustomerRepository;
import com.gabrielrnascimento.picpaydesafiobackend.infra.persistence.wallet.WalletRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    @Bean
    public CustomerEntityMapper customerEntityMapper() {
        return new CustomerEntityMapper();
    }

    @Bean
    public ICustomerGateway customerGateway(CustomerRepository customerRepository, CustomerEntityMapper customerEntityMapper) {
        return new CustomerRepositoryGateway(customerRepository, customerEntityMapper);
    }

    @Bean
    public IValidatePayerIsCustomerUseCase validatePayerIsCustomerUseCase(ICustomerGateway customerGateway) {
        return new ValidatePayerIsCustomerUseCase(customerGateway);
    }

    @Bean
    public WalletEntityMapper walletEntityMapper(CustomerRepository customerRepository) {
        return new WalletEntityMapper(customerRepository);
    }

    @Bean
    public IWalletGateway walletGateway(WalletRepository walletRepository, WalletEntityMapper walletEntityMapper) {
        return new WalletRepositoryGateway(walletRepository, walletEntityMapper);
    }

    @Bean
    public IGetWalletUseCase getWalletUseCase(IWalletGateway walletGateway) {
        return new GetWalletUseCase(walletGateway);
    }

    @Bean
    public ICreditWalletUseCase creditWalletUseCase(IWalletGateway walletGateway) {
        return new CreditWalletUseCase(walletGateway);
    }

    @Bean
    public IDebitWalletUseCase debitWalletUseCase(IWalletGateway walletGateway) {
        return new DebitWalletUseCase(walletGateway);
    }
}
