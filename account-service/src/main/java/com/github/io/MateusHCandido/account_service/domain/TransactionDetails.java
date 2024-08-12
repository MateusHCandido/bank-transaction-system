package com.github.io.MateusHCandido.account_service.domain;

import com.github.io.MateusHCandido.account_service.domain.enums.TransactionStatus;

public class TransactionDetails {

    private Long numberAccountSender;
    private Long numberAccountReceiver;
    private String emailAccountSender;
    private String emailAccountReceiver;
    private String transactionMessage;
    private TransactionStatus transactionStatus;

    public TransactionDetails(Long numberAccountSender, Long numberAccountReceiver, String emailAccountSender,
                              String emailAccountReceiver, String transactionMessage, TransactionStatus transactionStatus) {
        this.numberAccountSender = numberAccountSender;
        this.numberAccountReceiver = numberAccountReceiver;
        this.emailAccountSender = emailAccountSender;
        this.emailAccountReceiver = emailAccountReceiver;
        this.transactionMessage = transactionMessage;
        this.transactionStatus = transactionStatus;
    }

    public TransactionDetails() {

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

    public String getEmailAccountSender() {
        return emailAccountSender;
    }

    public void setEmailAccountSender(String emailAccountSender) {
        this.emailAccountSender = emailAccountSender;
    }

    public String getEmailAccountReceiver() {
        return emailAccountReceiver;
    }

    public void setEmailAccountReceiver(String emailAccountReceiver) {
        this.emailAccountReceiver = emailAccountReceiver;
    }

    public String getTransactionMessage() {
        return transactionMessage;
    }

    public void setTransactionMessage(String transactionMessage) {
        this.transactionMessage = transactionMessage;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
