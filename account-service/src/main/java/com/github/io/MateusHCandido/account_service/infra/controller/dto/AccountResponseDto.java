package com.github.io.MateusHCandido.account_service.infra.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {
    private String accountNumber;
    private String accountBalance;
    private String accountType;
    private String accountName;
    private String accountCpfCnpj;
    private String accountEmail;
    private String bankCode;
}
