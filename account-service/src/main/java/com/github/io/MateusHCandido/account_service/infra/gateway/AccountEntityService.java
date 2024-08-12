package com.github.io.MateusHCandido.account_service.infra.gateway;

import com.github.io.MateusHCandido.account_service.application.gateways.AppAccountRepository;
import com.github.io.MateusHCandido.account_service.application.usecases.account.exception.BankAccountNotFoundException;
import com.github.io.MateusHCandido.account_service.application.usecases.account.exception.EmailAlreadyExistsException;
import com.github.io.MateusHCandido.account_service.application.usecases.transaction.exception.TransactionFailureException;
import com.github.io.MateusHCandido.account_service.configuration.AccountMapper;
import com.github.io.MateusHCandido.account_service.domain.Account;
import com.github.io.MateusHCandido.account_service.infra.gateway.exception.CpfCnpjAlreadyExistException;
import com.github.io.MateusHCandido.account_service.infra.persistence.AccountEntity;
import com.github.io.MateusHCandido.account_service.infra.persistence.repository.AccountEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountEntityService implements AppAccountRepository {

    @Autowired
    private AccountEntityRepository repository;

    @Autowired
    private AccountMapper mapper;


    @Override
    public Optional<Account> findByAccountNumber(Long accountNumber) {
        AccountEntity entity = repository.findById(accountNumber)
                .orElseThrow(() -> new BankAccountNotFoundException("Account not found"));

        return mapper.convertToDomain(entity);
    }

    @Override
    public Optional<Account> findByAccountEmail(String accountEmail) {
        AccountEntity entity = repository.findByAccountEmail(accountEmail)
                .orElseThrow(()-> new BankAccountNotFoundException("Account not found"));

        return mapper.convertToDomain(entity);
    }

    @Override
    public Optional<Account> findByAccountCpfCnpj(String accountCpfCnpj) {
        AccountEntity entity = repository.findByAccountCpfCnpj(accountCpfCnpj)
                .orElseThrow(() -> new BankAccountNotFoundException("Account not found"));


        return mapper.convertToDomain(entity);
    }

    @Override
    public Account updateEmailAccount(Long accountNumber, String accountEmail) {
        boolean emailAlreadyExist = repository.existsByAccountEmail(accountEmail);
        boolean accountExist = repository.existsByAccountNumber(accountNumber);

        if (!accountExist) throw new BankAccountNotFoundException("Account not found");
        if (emailAlreadyExist) throw new EmailAlreadyExistsException("Email already exist");

        Optional<Account> account = findByAccountNumber(accountNumber);
        account.get().updateEmail(accountEmail);

        repository.updateAccountEmailByAccountNumber(accountEmail, accountNumber);

        return account.get();
    }

    @Override
    public Account saveAccount(Account account) {
        boolean cpfCnpjAlreadyExists = repository.existsByAccountCpfCnpj(account.getAccountCpfCnpj());
        boolean emailAlreadyExist = repository.existsByAccountEmail(account.getAccountEmail());

        if (cpfCnpjAlreadyExists) throw new CpfCnpjAlreadyExistException("CPF/CNPJ already exist");
        if (emailAlreadyExist) throw new EmailAlreadyExistsException("Email already exist");

        AccountEntity entity = mapper.convertToEntity(account);
        repository.save(entity);

        return mapper.convertToDomain(entity).get();
    }

    public String deposit(Long numberAccount, BigDecimal valueForDeposit){
        Account account = findByAccountNumber(numberAccount)
                .orElseThrow(() -> new BankAccountNotFoundException("account not found"));

        checkTransferValue(valueForDeposit);

        account.receiveValue(valueForDeposit);

        AccountEntity entity = mapper.convertToEntity(account);
        repository.updateAccountBalanceByAccountNumber(valueForDeposit, entity);

        return "updated bank account balance";
    }

    protected static void checkTransferValue(BigDecimal value){
        boolean isNegative = value.signum() == -1;
        if(isNegative) throw new TransactionFailureException("The transferred value is negative");
    }
}
