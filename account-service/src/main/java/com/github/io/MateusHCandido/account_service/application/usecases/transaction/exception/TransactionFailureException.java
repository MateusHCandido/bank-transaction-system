package com.github.io.MateusHCandido.account_service.application.usecases.transaction.exception;

public class TransactionFailureException extends RuntimeException {
    public TransactionFailureException(String message) {
        super(message);
    }
}
