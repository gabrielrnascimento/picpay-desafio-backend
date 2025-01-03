package com.gabrielrnascimento.picpaydesafiobackend.infra.http.feign.authorization;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "TransactionAuthorizationFeignClient", url = "${transaction.authorization.url}")
public interface TransactionAuthorizationFeignClient {

    @GetMapping("/authorize")
    TransactionAuthorizationDTO authorize();
}
