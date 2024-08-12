package com.github.io.MateusHCandido.account_service.infra.gateway;

import com.github.io.MateusHCandido.account_service.application.gateways.AppTransactionRepository;
import com.github.io.MateusHCandido.account_service.application.usecases.account.exception.BankAccountNotFoundException;
import com.github.io.MateusHCandido.account_service.configuration.TransactionMapper;
import com.github.io.MateusHCandido.account_service.domain.Account;
import com.github.io.MateusHCandido.account_service.domain.Transaction;
import com.github.io.MateusHCandido.account_service.domain.TransactionDetails;
import com.github.io.MateusHCandido.account_service.infra.persistence.AccountEntity;
import com.github.io.MateusHCandido.account_service.infra.persistence.TransactionEntity;
import com.github.io.MateusHCandido.account_service.infra.persistence.repository.AccountEntityRepository;
import com.github.io.MateusHCandido.account_service.infra.persistence.repository.TransactionEntityRepository;
import com.github.io.MateusHCandido.account_service.message.KafkaProducerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class TransactionEntityService implements AppTransactionRepository {

    @Autowired
    private TransactionEntityRepository transactionRepository;

    @Autowired
    private AccountEntityRepository accountRepository;

    @Autowired
    private AccountEntityService accountEntityService;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private KafkaProducerMessage kafkaProducerMessage;

    @Override
    public Transaction transfer(Transaction transaction) {
        Account accountSender = findAccountDomainByAccountNumber(transaction.getNumberAccountSender());
        Account accountReceiver = findAccountDomainByAccountNumber(transaction.getNumberAccountReceiver());

        accountSender.transferValue(transaction.getTransferredValue());
        accountReceiver.receiveValue(transaction.getTransferredValue());

        BigDecimal currentSenderAccountBalance = accountSender.getAccountBalance();
        BigDecimal currentReceiverAccountBalance = accountReceiver.getAccountBalance();

        AccountEntity senderEntity = findAccountEntityByAccountNumber(transaction.getNumberAccountSender());
        AccountEntity receiverEntity = findAccountEntityByAccountNumber(transaction.getNumberAccountReceiver());

        senderEntity.setAccountBalance(currentSenderAccountBalance);
        receiverEntity.setAccountBalance(currentReceiverAccountBalance);

        TransactionEntity entity = transactionMapper.convertToEntity(transaction, senderEntity, receiverEntity);

        transactionRepository.save(entity);
        TransactionDetails transactionDetails = generateTransactionLog(entity);
        kafkaProducerMessage.sendMessage(transactionDetails);
        return transaction;
    }


    protected AccountEntity findAccountEntityByAccountNumber(Long accountNumber){
        return accountRepository.findById(accountNumber)
                .orElseThrow(() -> new BankAccountNotFoundException("account not found"));
    }

    protected Account findAccountDomainByAccountNumber(Long accountNumber){
        return accountEntityService.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new BankAccountNotFoundException("account not found"));
    }

    protected TransactionDetails generateTransactionLog(TransactionEntity entity){
        Long numberAccountSender = entity.getNumberAccountSender().getAccountNumber();
        Long numberAccountReceiver = entity.getNumberAccountReceiver().getAccountNumber();

        String emailAccountSender = entity.getNumberAccountSender().getAccountEmail();
        String emailAccountReceiver = entity.getNumberAccountReceiver().getAccountEmail();

        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setNumberAccountSender(numberAccountSender);
        transactionDetails.setNumberAccountReceiver(numberAccountReceiver);
        transactionDetails.setEmailAccountSender(emailAccountSender);
        transactionDetails.setEmailAccountReceiver(emailAccountReceiver);
        transactionDetails.setTransactionStatus(entity.getTransactionStatus());
        transactionDetails.setTransactionMessage(entity.getTransactionMessage());

        return transactionDetails;
    }




}
