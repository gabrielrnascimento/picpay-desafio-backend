package com.gabrielrnascimento.picpaydesafiobackend.infra.events;

import java.math.BigDecimal;
import java.util.UUID;


public record TransactionCompleteDTO(UUID payerId, UUID payeeId, BigDecimal amount, String status) {

}
