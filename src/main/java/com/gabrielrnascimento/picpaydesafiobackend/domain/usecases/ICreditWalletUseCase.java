package com.gabrielrnascimento.picpaydesafiobackend.domain.usecases;

import java.math.BigDecimal;


public interface ICreditWalletUseCase {

    void credit(BigDecimal amount);
}
