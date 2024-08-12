package com.github.io.MateusHCandido.account_service.infra.controller.dto;

import com.github.io.MateusHCandido.account_service.domain.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseBody {

    private Long numberAccountSender;
    private Long numberAccountReceiver;
    private BigDecimal transferredValue;
    private TransactionStatus transactionStatus;
    private String transactionMessage;
    private LocalDate timestampTransaction;
}
