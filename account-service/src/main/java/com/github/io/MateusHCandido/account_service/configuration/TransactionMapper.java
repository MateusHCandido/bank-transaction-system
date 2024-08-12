package com.github.io.MateusHCandido.account_service.configuration;

import com.github.io.MateusHCandido.account_service.domain.Transaction;

import com.github.io.MateusHCandido.account_service.infra.controller.dto.TransactionResponseBody;
import com.github.io.MateusHCandido.account_service.infra.persistence.AccountEntity;
import com.github.io.MateusHCandido.account_service.infra.persistence.TransactionEntity;
import com.github.io.MateusHCandido.account_service.infra.persistence.repository.AccountEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    @Autowired
    private AccountEntityRepository accountRepository;

    public TransactionEntity convertToEntity(Transaction domain, AccountEntity sender, AccountEntity receiver){
        TransactionEntity entity = new TransactionEntity();
        entity.setNumberAccountSender(sender);
        entity.setNumberAccountReceiver(receiver);
        entity.setTransferredValue(domain.getTransferredValue());
        entity.setTransactionStatus(domain.getTransactionStatus());
        entity.setTimestampTransaction(domain.getTimestampTransaction());
        entity.setTransactionMessage(domain.getTransactionMessage());
        return entity;
    }


    public TransactionResponseBody convertToResponseBody(Transaction domain) {
        TransactionResponseBody response = new TransactionResponseBody();

        response.setNumberAccountSender(domain.getNumberAccountSender());
        response.setNumberAccountReceiver(domain.getNumberAccountReceiver());
        response.setTransferredValue(domain.getTransferredValue());
        response.setTransactionStatus(domain.getTransactionStatus());
        response.setTimestampTransaction(domain.getTimestampTransaction());
        response.setTransactionMessage(domain.getTransactionMessage());

        return response;
    }
}
