package com.github.io.MateusHCandido.account_service.infra.gateway.exception;

public class CpfCnpjAlreadyExistException extends RuntimeException{
    public CpfCnpjAlreadyExistException(String message){
        super(message);
    }
}
