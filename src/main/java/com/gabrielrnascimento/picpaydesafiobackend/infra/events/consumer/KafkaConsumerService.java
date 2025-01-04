package com.gabrielrnascimento.picpaydesafiobackend.infra.events.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.INotificationGateway;
import com.gabrielrnascimento.picpaydesafiobackend.infra.events.TransactionCompleteDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumerService {

    private final INotificationGateway notificationGateway;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaConsumerService(INotificationGateway notificationGateway) {
        this.notificationGateway = notificationGateway;
    }

    @KafkaListener(topics = "payments", groupId = "group_id")
    public void consume(String message) {
        try {
            TransactionCompleteDTO transactionCompleteDTO = objectMapper.readValue(message, TransactionCompleteDTO.class);
            notificationGateway.notifyTransactionComplete(transactionCompleteDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
