package com.github.io.MateusHCandido.bank_service_10.infra.controller.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BankDtoResponse {

    @NotNull(message = "The bank code field cannot be null")
    private Integer bankCode;
    @NotBlank(message = "The bank name field cannot be blank")
    private String bankName;

    public BankDtoResponse(Integer responseBankCode, String responseBankName){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        checkResponse(responseBankCode, responseBankName, validator);

        this.bankCode = responseBankCode;
        this.bankName = responseBankName;
    }

    protected void checkResponse(Integer code, String name, Validator validator){
        BankDtoRequest dto = new BankDtoRequest(code, name);
        Set<ConstraintViolation<BankDtoRequest>> violations = validator.validate(dto);

        if (! violations.isEmpty()){
            throw new RuntimeException("An error occurred creating the DtoResponse in the codeBank parameter");
        }
    }

}
