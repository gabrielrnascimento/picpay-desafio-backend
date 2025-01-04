package com.gabrielrnascimento.picpaydesafiobackend.application.gateways;

import com.gabrielrnascimento.picpaydesafiobackend.infra.events.TransactionCompleteDTO;


public interface INotificationGateway {

    void notifyTransactionComplete(TransactionCompleteDTO transaction);
}
