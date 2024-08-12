package com.github.io.MateusHCandido.account_service.application.usecases.account;

import com.github.io.MateusHCandido.account_service.application.gateways.AppAccountRepository;
import com.github.io.MateusHCandido.account_service.application.usecases.account.exception.BankAccountNotFoundException;
import com.github.io.MateusHCandido.account_service.domain.Account;

import java.util.Optional;

public class FindByAccountCpfCnpjUseCase {

    private final AppAccountRepository repository;

    public FindByAccountCpfCnpjUseCase(AppAccountRepository repository) {
        this.repository = repository;
    }

    public Account findByAccountCpfCnpjUseCase(String cpfCnpj){
        Optional<Account> account = repository.findByAccountCpfCnpj(cpfCnpj);

        if (!account.isPresent()){
            throw new BankAccountNotFoundException("Account not found");
        }

        return account.get();
    }
}
