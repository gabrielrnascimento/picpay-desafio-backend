package com.gabrielrnascimento.picpaydesafiobackend.domain.entities;

import java.math.BigDecimal;
import java.util.UUID;


public record Wallet(UUID id, BigDecimal balance, UUID userId) {

}
