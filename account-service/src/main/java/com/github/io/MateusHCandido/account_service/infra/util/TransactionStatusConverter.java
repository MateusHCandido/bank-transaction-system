package com.github.io.MateusHCandido.account_service.infra.util;

import com.github.io.MateusHCandido.account_service.domain.enums.TransactionStatus;
import com.github.io.MateusHCandido.account_service.infra.util.exception.TransactionStatusException;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
@Component
public class TransactionStatusConverter implements AttributeConverter<TransactionStatus, String> {

    @Override
    public String convertToDatabaseColumn(TransactionStatus attribute) {
        if (attribute == null) {
            throw new TransactionStatusException("the type of transaction is not informed");
        }
        return attribute.getDescription();
    }

    @Override
    public TransactionStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            throw new TransactionStatusException("the type of transaction is not informed");
        }

        switch (dbData) {
            case "S":
                    return TransactionStatus.SUCCESSFUL;
            case "F":
                return TransactionStatus.FAILURE;
            default:
                throw new IllegalArgumentException("Unknown value: " + dbData);
        }
    }
}