package com.gabrielrnascimento.picpaydesafiobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class PicpayDesafioBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PicpayDesafioBackendApplication.class, args);
    }

}
