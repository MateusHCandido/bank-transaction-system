package com.github.io.MateusHCandido.account_service.infra.util.exception;

public class TransactionStatusException extends RuntimeException{
    public TransactionStatusException(String message) {
        super(message);
    }
}
