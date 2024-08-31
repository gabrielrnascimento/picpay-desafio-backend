package com.gabrielrnascimento.picpaydesafiobackend.infra.controllers;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;


public record TransactionRequestDTO(
    @NotNull BigDecimal value,
    @NotNull UUID payer,
    @NotNull UUID payee
) {

}
