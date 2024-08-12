package com.github.io.MateusHCandido.account_service.application.usecases.account;

import com.github.io.MateusHCandido.account_service.application.gateways.AppAccountRepository;
import com.github.io.MateusHCandido.account_service.application.usecases.account.exception.BankAccountNotFoundException;
import com.github.io.MateusHCandido.account_service.domain.Account;

import java.util.Optional;

public class FindByAccountNumberUseCase {

    private final AppAccountRepository repository;

    public FindByAccountNumberUseCase(AppAccountRepository repository) {
        this.repository = repository;
    }

    public Account findByAccountNumberUseCase(Long accountNumber) {
        Optional<Account> account = repository.findByAccountNumber(accountNumber);

        if (!account.isPresent()){
            throw new BankAccountNotFoundException("Bank not found");
        }

        return account.get();
    }
}
