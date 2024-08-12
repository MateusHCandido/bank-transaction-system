package com.github.io.MateusHCandido.account_service.infra.persistence.util;

import com.github.io.MateusHCandido.account_service.domain.enums.AccountType;
import com.github.io.MateusHCandido.account_service.domain.exception.AccountTypeException;
import com.github.io.MateusHCandido.account_service.infra.util.AccountTypeConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTypeConverterTest {

    private final AccountTypeConverter converter = new AccountTypeConverter();

    @Test
    public void testConvertToDatabaseColumn() {
        assertEquals("N", converter.convertToDatabaseColumn(AccountType.NATURAL_PERSON));
        assertEquals("L", converter.convertToDatabaseColumn(AccountType.LEGAL_PERSON));
    }

    @Test
    public void testConvertToDatabaseColumnWithNull() {
        Exception exception = assertThrows(AccountTypeException.class, () -> converter.convertToDatabaseColumn(null));
        assertEquals("the type of account is not informed", exception.getMessage());
    }

    @Test
    public void testConvertToEntityAttribute() {
        assertEquals(AccountType.NATURAL_PERSON, converter.convertToEntityAttribute("N"));
        assertEquals(AccountType.LEGAL_PERSON, converter.convertToEntityAttribute("L"));
    }

    @Test
    public void testConvertToEntityAttributeWithInvalidValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> converter.convertToEntityAttribute("X"));
        assertEquals("Unknown value: X", exception.getMessage());
    }

    @Test
    public void testConvertToEntityAttributeWithNull() {
        Exception exception = assertThrows(AccountTypeException.class, () -> converter.convertToEntityAttribute(null));
        assertEquals("the type of account is not informed", exception.getMessage());
    }

    @Test
    public void testConvertToEntityAttributeWithEmptyString() {
        Exception exception = assertThrows(AccountTypeException.class, () -> converter.convertToEntityAttribute(""));
        assertEquals("the type of account is not informed", exception.getMessage());
    }
}