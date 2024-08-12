package com.github.io.MateusHCandido.account_service.application.usecases.account;

import com.github.io.MateusHCandido.account_service.application.gateways.AppAccountRepository;
import com.github.io.MateusHCandido.account_service.application.usecases.account.exception.BankAccountNotFoundException;
import com.github.io.MateusHCandido.account_service.domain.Account;

import java.util.Optional;

public class FindByAccountEmailUseCase {

    private final AppAccountRepository repository;

    public FindByAccountEmailUseCase(AppAccountRepository repository) {
        this.repository = repository;
    }

    public Account findByAccountEmailUseCase(String email) {
        Optional<Account> account = repository.findByAccountEmail(email);

        if(!account.isPresent()){
            throw new BankAccountNotFoundException("Account not found");
        }

        return account.get();
    }
}
