package com.github.io.MateusHCandido.bank_service_10.infra.gateway.exception;

public class ListIsEmptyException extends RuntimeException {
    public ListIsEmptyException(String message) {
        super(message);
    }
}
