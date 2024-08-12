package com.github.io.MateusHCandido.account_service.domain;

import com.github.io.MateusHCandido.account_service.domain.enums.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

    private Long numberAccountSender;
    private Long numberAccountReceiver;
    private BigDecimal transferredValue;
    private TransactionStatus transactionStatus;
    private String transactionMessage;
    private LocalDate timestampTransaction;

    public Transaction(){}

    public Transaction(Long numberAccountSender, Long numberAccountReceiver,
                       BigDecimal valueForTransfer, TransactionStatus transactionStatus, String transactionMessage) {
        this.numberAccountSender = numberAccountSender;
        this.numberAccountReceiver = numberAccountReceiver;
        this.transferredValue = valueForTransfer;
        this.transactionStatus = transactionStatus;
        this.timestampTransaction = LocalDate.now();
        this.transactionMessage = transactionMessage;
    }

    public Long getNumberAccountSender() {
        return numberAccountSender;
    }

    public void setNumberAccountSender(Long numberAccountSender) {
        this.numberAccountSender = numberAccountSender;
    }

    public Long getNumberAccountReceiver() {
        return numberAccountReceiver;
    }

    public void setNumberAccountReceiver(Long numberAccountReceiver) {
        this.numberAccountReceiver = numberAccountReceiver;
    }

    public BigDecimal getTransferredValue() {
        return transferredValue;
    }

    public void setTransferredValue(BigDecimal transferredValue) {
        this.transferredValue = transferredValue;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionMessage() {
        return transactionMessage;
    }

    public void setTransactionMessage(String transactionMessage) {
        this.transactionMessage = transactionMessage;
    }

    public LocalDate getTimestampTransaction() {
        return timestampTransaction;
    }

    public void setTimestampTransaction(LocalDate timestampTransaction) {
        this.timestampTransaction = timestampTransaction;
    }
}
