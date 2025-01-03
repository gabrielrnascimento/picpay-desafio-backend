package com.gabrielrnascimento.picpaydesafiobackend.infra.controllers;

import com.gabrielrnascimento.picpaydesafiobackend.domain.exceptions.InsufficientFundsException;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.ICreditWalletUseCase;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.IDebitWalletUseCase;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.IGetWalletUseCase;
import com.gabrielrnascimento.picpaydesafiobackend.domain.usecases.IValidatePayerIsCustomerUseCase;
import com.gabrielrnascimento.picpaydesafiobackend.infra.gateways.authorization.AuthorizationGateway;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final AuthorizationGateway authorizationGateway;
    private final IValidatePayerIsCustomerUseCase validatePayerIsCustomerUseCase;
    private final IGetWalletUseCase getWalletUseCase;
    private final ICreditWalletUseCase creditWalletUseCase;
    private final IDebitWalletUseCase debitWalletUseCase;

    public TransferController(AuthorizationGateway authorizationGateway, IValidatePayerIsCustomerUseCase validatePayerIsCustomerUseCase, IGetWalletUseCase getWalletUseCase, ICreditWalletUseCase creditWalletUseCase, IDebitWalletUseCase debitWalletUseCase) {
        this.authorizationGateway = authorizationGateway;
        this.validatePayerIsCustomerUseCase = validatePayerIsCustomerUseCase;
        this.getWalletUseCase = getWalletUseCase;
        this.creditWalletUseCase = creditWalletUseCase;
        this.debitWalletUseCase = debitWalletUseCase;
    }

    @PostMapping
    @Transactional
    ResponseEntity<TransactionResponseDTO> generateTransaction(@Valid @RequestBody TransactionRequestDTO request) {
        var payerWallet = getWalletUseCase.getWallet(request.payer());
        if (payerWallet == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TransactionResponseDTO("Payer wallet not found"));
        }

        if (!validatePayerIsCustomerUseCase.isCustomer(request.payer())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TransactionResponseDTO("Payer is not a customer"));
        }

        var payeeWallet = getWalletUseCase.getWallet(request.payee());
        if (payeeWallet == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TransactionResponseDTO("Payee wallet not found"));
        }

        boolean result = authorizationGateway.isAuthorized();
        if (!result) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new TransactionResponseDTO("Transaction not authorized"));
        }

        try {
            debitWalletUseCase.debit(payerWallet, request.value());
        } catch (InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new TransactionResponseDTO("Payer has insufficient funds"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TransactionResponseDTO("Transaction failed"));
        }
        creditWalletUseCase.credit(payeeWallet, request.value());

        return ResponseEntity.ok(new TransactionResponseDTO("Transaction completed successfully"));
    }
}
