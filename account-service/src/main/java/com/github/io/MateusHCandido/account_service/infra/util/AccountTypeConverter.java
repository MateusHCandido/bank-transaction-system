package com.github.io.MateusHCandido.account_service.infra.util;

import com.github.io.MateusHCandido.account_service.domain.enums.AccountType;
import com.github.io.MateusHCandido.account_service.domain.exception.AccountTypeException;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
@Component
public class AccountTypeConverter implements AttributeConverter<AccountType, String> {

    @Override
    public String convertToDatabaseColumn(AccountType accountType) {
        if (accountType == null) {
            throw new AccountTypeException("the type of account is not informed");
        }
        return accountType.getDescription();
    }

    @Override
    public AccountType convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            throw new AccountTypeException("the type of account is not informed");
        }

        switch (dbData) {
            case "N":
                return AccountType.NATURAL_PERSON;
            case "L":
                return AccountType.LEGAL_PERSON;
            default:
                throw new IllegalArgumentException("Unknown value: " + dbData);
        }
    }
}