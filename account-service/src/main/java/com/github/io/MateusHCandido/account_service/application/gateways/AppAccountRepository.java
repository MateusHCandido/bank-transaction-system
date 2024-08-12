package com.github.io.MateusHCandido.account_service.application.gateways;

import com.github.io.MateusHCandido.account_service.domain.Account;

import java.util.Optional;


public interface AppAccountRepository {


    Optional<Account> findByAccountNumber(Long accountNumber);

    Optional<Account> findByAccountEmail(String accountEmail);

    Optional<Account> findByAccountCpfCnpj(String accountCpfCnpj);

    Account updateEmailAccount(Long numberAccount, String accountEmail);

    Account saveAccount(Account account);

}
