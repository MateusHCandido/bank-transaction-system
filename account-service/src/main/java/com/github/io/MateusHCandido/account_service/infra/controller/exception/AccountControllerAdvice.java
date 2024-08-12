package com.github.io.MateusHCandido.account_service.infra.controller.exception;

import com.github.io.MateusHCandido.account_service.application.usecases.account.exception.BankAccountNotFoundException;
import com.github.io.MateusHCandido.account_service.application.usecases.account.exception.CreateAccountException;
import com.github.io.MateusHCandido.account_service.application.usecases.account.exception.EmailAlreadyExistsException;
import com.github.io.MateusHCandido.account_service.application.usecases.transaction.exception.InsufficientAccountBalanceException;
import com.github.io.MateusHCandido.account_service.application.usecases.transaction.exception.SenderAndReceiverAccountDuplicateException;
import com.github.io.MateusHCandido.account_service.application.usecases.transaction.exception.TransactionFailureException;
import com.github.io.MateusHCandido.account_service.domain.exception.AccountTypeException;
import com.github.io.MateusHCandido.account_service.domain.exception.InvalidCnpjException;
import com.github.io.MateusHCandido.account_service.domain.exception.InvalidCpfException;
import com.github.io.MateusHCandido.account_service.domain.exception.InvalidEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountControllerAdvice {

    @ExceptionHandler(BankAccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleAccountNotFound(BankAccountNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(InvalidEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidEmail(InvalidEmailException e){
        return e.getMessage();
    }

    @ExceptionHandler(AccountTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleAccountTypeException(AccountTypeException e){
        return e.getMessage();
    }

    @ExceptionHandler(InvalidCnpjException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidCnpjException(InvalidCnpjException e){
        return e.getMessage();
    }

    @ExceptionHandler(InvalidCpfException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidCpfException(InvalidCpfException e){
        return e.getMessage();
    }

    @ExceptionHandler(CreateAccountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleCreateAccountException(CreateAccountException e){
        return e.getMessage();
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEmailAlreadyExist(EmailAlreadyExistsException e){
        return e.getMessage();
    }

    @ExceptionHandler(InsufficientAccountBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInsufficientAccountBalance(InsufficientAccountBalanceException e){
        return e.getMessage();
    }

    @ExceptionHandler(SenderAndReceiverAccountDuplicateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleSenderAndReceiverAccountDuplicate(SenderAndReceiverAccountDuplicateException e){
        return e.getMessage();
    }

    @ExceptionHandler(TransactionFailureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleTransactionFailure(TransactionFailureException e){
        return e.getMessage();
    }
}
