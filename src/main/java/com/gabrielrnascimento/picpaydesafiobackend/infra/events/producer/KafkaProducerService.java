package com.gabrielrnascimento.picpaydesafiobackend.infra.events.producer;

import com.gabrielrnascimento.picpaydesafiobackend.infra.events.TransactionCompleteDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducerService {

    private static final String TOPIC = "payments";

    private final KafkaTemplate<String, TransactionCompleteDTO> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, TransactionCompleteDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishMessage(TransactionCompleteDTO transaction) {
        kafkaTemplate.send(TOPIC, transaction);
    }
}
