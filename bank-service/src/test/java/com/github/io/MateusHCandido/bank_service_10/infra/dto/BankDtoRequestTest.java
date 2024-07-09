package com.github.io.MateusHCandido.bank_service_10.infra.dto;

import com.github.io.MateusHCandido.bank_service_10.infra.controller.dto.BankDtoRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class BankDtoRequestTest {

    private final Validator validator;

    public BankDtoRequestTest(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    @DisplayName("Should create a object dto request type with success")
    public void generateDtoRequestCase01(){
        BankDtoRequest dto = new BankDtoRequest(123, "test");

        Assertions.assertNotNull(dto);
        Assertions.assertTrue("test".equals(dto.getBankName()));
        Assertions.assertEquals(123, dto.getBankCode());
    }

    @Test
    @DisplayName("Should throw exception when trying to create a Dto with blank name")
    public void createBankDtoWithBlankName() {
        BankDtoRequest dto = new BankDtoRequest(123, "");
        Set<ConstraintViolation<BankDtoRequest>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    @DisplayName("Should throw exception when trying to create a Dto with null code")
    public void createBankDtoWithNullCode() {
        BankDtoRequest dto = new BankDtoRequest(null, "Valid Name");
        Set<ConstraintViolation<BankDtoRequest>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }
}
