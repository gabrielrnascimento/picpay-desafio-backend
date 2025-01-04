package com.gabrielrnascimento.picpaydesafiobackend.infra.http.feign.notification;

import com.gabrielrnascimento.picpaydesafiobackend.infra.events.TransactionCompleteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "TransactionNotificationFeignClient", url = "${transaction.notification.url}")
public interface TransactionNotificationFeignClient {

    @PostMapping("/notify")
    void notifyTransactionComplete(@RequestBody TransactionCompleteDTO transactionCompleteDTO);
}
