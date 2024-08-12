package com.github.io.MateusHCandido.account_service.application.usecases.account;

import com.github.io.MateusHCandido.account_service.application.gateways.AppAccountRepository;
import com.github.io.MateusHCandido.account_service.domain.Account;

public class SaveAccountUseCase {

    private final AppAccountRepository repository;

    public SaveAccountUseCase(AppAccountRepository repository) {
        this.repository = repository;
    }

    public Account saveAccountUseCase(Account account) {
        return repository.saveAccount(account);
    }

}
