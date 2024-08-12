package com.github.io.MateusHCandido.account_service.application.usecases.transaction.exception;

public class SenderAndReceiverAccountDuplicateException extends RuntimeException {
    public SenderAndReceiverAccountDuplicateException(String message) {
        super(message);
    }
}
