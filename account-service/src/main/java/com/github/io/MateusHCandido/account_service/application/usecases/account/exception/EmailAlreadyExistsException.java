package com.github.io.MateusHCandido.account_service.application.usecases.account.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
