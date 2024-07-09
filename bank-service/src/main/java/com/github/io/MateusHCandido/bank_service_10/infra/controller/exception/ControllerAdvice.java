package com.github.io.MateusHCandido.bank_service_10.infra.controller.exception;

import com.github.io.MateusHCandido.bank_service_10.core.exception.BankNameIsBlankException;
import com.github.io.MateusHCandido.bank_service_10.core.exception.BankNotFoundException;
import com.github.io.MateusHCandido.bank_service_10.core.exception.CreateBankException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(CreateBankException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleCreateBankException(CreateBankException exception){
        String exceptionMessage = exception.getMessage();
        if (exceptionMessage.contains("duplicate key value violates unique constraint")){
            return "Error when creating bank: Bank code entered is already registered";
        }
        return "he bank name field cannot be blank";
    }

    @ExceptionHandler(BankNameIsBlankException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBankNameIsBlankExceptionException(BankNameIsBlankException exception){
        return exception.getMessage();
    }
    
    @ExceptionHandler(BankNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBankNotFoundExceptionException(BankNotFoundException exception){
        return exception.getMessage();
    }
}
