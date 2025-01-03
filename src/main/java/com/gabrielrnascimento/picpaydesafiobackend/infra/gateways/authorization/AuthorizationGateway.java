package com.gabrielrnascimento.picpaydesafiobackend.infra.gateways.authorization;

import com.gabrielrnascimento.picpaydesafiobackend.application.gateways.IAuthorizationGateway;
import com.gabrielrnascimento.picpaydesafiobackend.infra.http.feign.authorization.TransactionAuthorizationDTO;
import com.gabrielrnascimento.picpaydesafiobackend.infra.http.feign.authorization.TransactionAuthorizationFeignClient;
import org.springframework.stereotype.Service;


@Service
public class AuthorizationGateway implements IAuthorizationGateway {

    private final TransactionAuthorizationFeignClient transactionAuthorizationFeignClient;

    public AuthorizationGateway(TransactionAuthorizationFeignClient transactionAuthorizationFeignClient) {
        this.transactionAuthorizationFeignClient = transactionAuthorizationFeignClient;
    }

    @Override
    public boolean isAuthorized() {
        TransactionAuthorizationDTO result = transactionAuthorizationFeignClient.authorize();
        return AuthorizationStatus.fromString(result.message()).equals(AuthorizationStatus.AUTHORIZED);
    }
}
