package com.github.io.MateusHCandido.account_service.application.usecases.account;

import com.github.io.MateusHCandido.account_service.application.gateways.AppAccountRepository;
import com.github.io.MateusHCandido.account_service.domain.Account;

public class UpdateEmailAccountUseCase {

    private final AppAccountRepository repository;

    public UpdateEmailAccountUseCase(AppAccountRepository repository) {
        this.repository = repository;
    }

    public Account updateEmailAccountUseCase(Long accountNumber, String newEmail) {
        return repository.updateEmailAccount(accountNumber, newEmail);
    }
}
