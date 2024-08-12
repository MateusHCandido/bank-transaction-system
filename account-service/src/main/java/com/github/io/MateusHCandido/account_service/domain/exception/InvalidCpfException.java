package com.github.io.MateusHCandido.account_service.domain.exception;

public class InvalidCpfException extends RuntimeException{
    public InvalidCpfException(String message){
        super(message);
    }
}
