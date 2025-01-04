package com.gabrielrnascimento.picpaydesafiobackend.infra.gateways.notification;

import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.INotificationGateway;
import com.gabrielrnascimento.picpaydesafiobackend.infra.events.TransactionCompleteDTO;
import com.gabrielrnascimento.picpaydesafiobackend.infra.events.producer.KafkaProducerService;
import com.gabrielrnascimento.picpaydesafiobackend.infra.http.feign.notification.TransactionNotificationFeignClient;
import feign.FeignException;
import org.springframework.stereotype.Service;


@Service
public class NotificationGateway implements INotificationGateway {

    private final TransactionNotificationFeignClient transactionNotificationFeignClient;
    private final KafkaProducerService kafkaProducerService;

    public NotificationGateway(TransactionNotificationFeignClient transactionNotificationFeignClient, KafkaProducerService kafkaProducerService) {
        this.transactionNotificationFeignClient = transactionNotificationFeignClient;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public void notifyTransactionComplete(TransactionCompleteDTO transaction) {
        try {
            transactionNotificationFeignClient.notifyTransactionComplete(transaction);
        } catch (FeignException e) {
            kafkaProducerService.publishMessage(transaction);
        }
    }
}
