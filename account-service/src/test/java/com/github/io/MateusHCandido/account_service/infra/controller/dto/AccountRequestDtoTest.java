package com.github.io.MateusHCandido.account_service.infra.controller.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AccountRequestDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldPassValidationWhenAllFieldsAreValid() {
        AccountRequestDto dto = new AccountRequestDto(
                "L",
                "Test Account",
                "12345678910",
                "test@example.com",
                123L
        );

        Set<ConstraintViolation<AccountRequestDto>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWhenAccountTypeIsNull() {
        AccountRequestDto dto = new AccountRequestDto(
                null,
                "Test Account",
                "12345678910",
                "test@example.com",
                123L
        );

        Set<ConstraintViolation<AccountRequestDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertEquals("Account type cannot be null", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenAccountNameIsNull() {
        AccountRequestDto dto = new AccountRequestDto(
                "L",
                null,
                "12345678910",
                "test@example.com",
                123L
        );

        Set<ConstraintViolation<AccountRequestDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertEquals("Account name cannot be null", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenAccountCpfCnpjIsNull() {
        AccountRequestDto dto = new AccountRequestDto(
                "L",
                "Test Account",
                null,
                "test@example.com",
                123L
        );

        Set<ConstraintViolation<AccountRequestDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertEquals("CPF/CNPJ cannot be null", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenAccountEmailIsNull() {
        AccountRequestDto dto = new AccountRequestDto(
                "L",
                "Test Account",
                "12345678910",
                null,
                123L
        );

        Set<ConstraintViolation<AccountRequestDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertEquals("Email cannot be null", violations.iterator().next().getMessage());
    }

    @Test
    void shouldFailValidationWhenBankCodeIsNull() {
        AccountRequestDto dto = new AccountRequestDto(
                "L",
                "Test Account",
                "12345678910",
                "test@example.com",
                null
        );

        Set<ConstraintViolation<AccountRequestDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertEquals("Bank code cannot be null", violations.iterator().next().getMessage());
    }


    @Test
    void shouldFailValidationWhenAccountNameLengthIsInvalid() {
        AccountRequestDto dto = new AccountRequestDto(
                "L",
                "",  // invalid length
                "12345678910",
                "test@example.com",
                123L
        );

        Set<ConstraintViolation<AccountRequestDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Account name must be between 1 and 100 characters")));
    }

    @Test
    void shouldFailValidationWhenAccountCpfCnpjLengthIsInvalid() {
        AccountRequestDto dto = new AccountRequestDto(
                "L",
                "Test Account",
                "123",  // invalid length
                "test@example.com",
                123L
        );

        Set<ConstraintViolation<AccountRequestDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("CPF/CNPJ must be between 11 and 14 characters")));
    }

    @Test
    void shouldFailValidationWhenAccountEmailIsInvalid() {
        AccountRequestDto dto = new AccountRequestDto(
                "L",
                "Test Account",
                "12345678910",
                "invalid-email",  // invalid email
                123L
        );

        Set<ConstraintViolation<AccountRequestDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Email should be valid")));
    }
}