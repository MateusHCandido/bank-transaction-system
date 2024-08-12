package com.github.io.MateusHCandido.account_service.infra.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDto {

    @NotNull(message = "Account type cannot be null")
    @Size(max = 14, message = "set L or P for account type")
    private String accountType;

    @NotNull(message = "Account name cannot be null")
    @Size(min = 1, max = 100, message = "Account name must be between 1 and 100 characters")
    private String accountName;

    @NotNull(message = "CPF/CNPJ cannot be null")
    @Size(min = 11, max = 14, message = "CPF/CNPJ must be between 11 and 14 characters")
    private String accountCpfCnpj;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String accountEmail;

    @NotNull(message = "Bank code cannot be null")
    private Long bankCode;
}
