package com.github.io.MateusHCandido.account_service.application.usecases.transaction.exception;

public class InsufficientAccountBalanceException extends RuntimeException {
    public InsufficientAccountBalanceException(String message) {
        super(message);
    }
}
