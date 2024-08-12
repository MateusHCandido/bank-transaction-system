package com.github.io.MateusHCandido.transaction_log_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long transactionDetailId;
    private Long numberAccountSender;
    private Long numberAccountReceiver;
    private String emailAccountSender;
    private String emailAccountReceiver;
    private String transactionMessage;
    private String transactionStatus;
}
