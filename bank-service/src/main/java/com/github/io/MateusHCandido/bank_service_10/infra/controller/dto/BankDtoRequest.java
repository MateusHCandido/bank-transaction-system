package com.github.io.MateusHCandido.bank_service_10.infra.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankDtoRequest {

    @NotNull(message = "The bank code field cannot be null")
    private Integer bankCode;
    @NotBlank(message = "The bank name field cannot be blank")
    private String bankName;

}
