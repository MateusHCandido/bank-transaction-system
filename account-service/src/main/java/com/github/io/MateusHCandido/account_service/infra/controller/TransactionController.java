package com.github.io.MateusHCandido.account_service.infra.controller;

import com.github.io.MateusHCandido.account_service.application.usecases.transaction.TransferUseCase;
import com.github.io.MateusHCandido.account_service.configuration.TransactionMapper;
import com.github.io.MateusHCandido.account_service.domain.Transaction;
import com.github.io.MateusHCandido.account_service.infra.controller.dto.TransactionRequestDto;
import com.github.io.MateusHCandido.account_service.infra.controller.dto.TransactionResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransferUseCase transferUseCase;

    @Autowired
    private TransactionMapper mapper;

    @PostMapping("/transfer")
    public TransactionResponseBody transfer(@RequestBody TransactionRequestDto dto){
        Long numberAccountSender = dto.getNumberAccountSender();
        Long numberAccountReceiver = dto.getNumberAccountReceiver();
        BigDecimal transferredValue = new BigDecimal( dto.getTransferredValue() );
        Transaction transaction = transferUseCase.transfer(numberAccountSender, numberAccountReceiver, transferredValue);

        return mapper.convertToResponseBody(transaction);
    }
}
