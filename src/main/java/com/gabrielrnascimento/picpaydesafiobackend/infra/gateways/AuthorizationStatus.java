package com.gabrielrnascimento.picpaydesafiobackend.infra.gateways;

public enum AuthorizationStatus {
    AUTHORIZED("Authorized"),
    UNAUTHORIZED("Unauthorized");

    private final String status;

    AuthorizationStatus(String status) {
        this.status = status;
    }

    public static AuthorizationStatus fromString(String message) {
        for (AuthorizationStatus value : AuthorizationStatus.values()) {
            if (value.status.equals(message)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid message");
    }

    public String getMessage() {
        return status;
    }
}
