package com.github.io.MateusHCandido.account_service.configuration;

import com.github.io.MateusHCandido.account_service.domain.Account;
import com.github.io.MateusHCandido.account_service.domain.enums.AccountType;
import com.github.io.MateusHCandido.account_service.infra.controller.dto.AccountRequestDto;
import com.github.io.MateusHCandido.account_service.infra.controller.dto.AccountResponseDto;
import com.github.io.MateusHCandido.account_service.infra.persistence.AccountEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountMapper {

    public Optional<Account> convertToDomain(AccountEntity entity){
        Account account = new Account();
        account.setBankCode( entity.getBankCode() );
        account.setAccountNumber( entity.getAccountNumber() );
        account.setAccountBalance( entity.getAccountBalance() );
        account.setAccountName( entity.getAccountName() );
        account.setAccountCpfCnpj( entity.getAccountCpfCnpj() );
        account.setAccountEmail( entity.getAccountEmail() );
        account.setAccountType( entity.getAccountType() );

        return Optional.of(account);
    }



    public AccountEntity convertToEntity(Account domain){
        AccountEntity entity = new AccountEntity();
        entity.setAccountNumber(domain.getAccountNumber());
        entity.setBankCode( domain.getBankCode() );
        entity.setAccountBalance( domain.getAccountBalance() );
        entity.setAccountName( domain.getAccountName() );
        entity.setAccountCpfCnpj( domain.getAccountCpfCnpj() );
        entity.setAccountEmail( domain.getAccountEmail() );
        entity.setAccountType( domain.getAccountType() );
        return entity;
    }

    public Account convertToDomain(AccountRequestDto dto){
        Account account = new Account();

        account.setBankCode( dto.getBankCode() );
        account.setAccountName( dto.getAccountName() );
        account.setAccountCpfCnpj( dto.getAccountCpfCnpj() );
        account.setAccountEmail( dto.getAccountEmail() );
        account.setAccountBalance(BigDecimal.ZERO);

        if (dto.getAccountType().equals("L")) account.setAccountType( AccountType.LEGAL_PERSON );
        if (dto.getAccountType().equals("N")) account.setAccountType( AccountType.NATURAL_PERSON );

        return account;
    }

    public AccountResponseDto convertToResponseBody(Account domain){
        AccountResponseDto dto = new AccountResponseDto();

        dto.setAccountNumber(String.valueOf(domain.getAccountNumber()));
        dto.setBankCode(String.valueOf(domain.getBankCode()));
        dto.setAccountBalance(String.valueOf(domain.getAccountBalance()));
        dto.setAccountName( domain.getAccountName() );
        dto.setAccountCpfCnpj( domain.getAccountCpfCnpj() );
        dto.setAccountEmail( domain.getAccountEmail() );
        dto.setAccountType( domain.getAccountType().getDescription() );

        return dto;
    }
}
