package com.github.io.MateusHCandido.account_service.infra.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestUpdateDto {

    @NotNull(message = "Bank code cannot be null")
    private Long accountNumber;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String newEmail;

}
