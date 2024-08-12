package com.github.io.MateusHCandido.account_service.application.usecases.account.exception;

public class BankAccountNotFoundException extends RuntimeException{
    public BankAccountNotFoundException(String message){
        super(message);
    }
}
