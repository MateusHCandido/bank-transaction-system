package com.github.io.MateusHCandido.account_service.infra.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDto {
    @NotNull(message = "the number account sender not is null")
    private Long numberAccountSender;
    @NotNull(message = "the number account receiver not is null")
    private Long numberAccountReceiver;

    @NotBlank(message = "the transferred value not is blank")
    private String transferredValue;
}
