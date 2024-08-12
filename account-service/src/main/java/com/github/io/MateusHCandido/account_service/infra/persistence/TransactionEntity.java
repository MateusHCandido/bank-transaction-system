package com.github.io.MateusHCandido.account_service.infra.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.io.MateusHCandido.account_service.domain.enums.TransactionStatus;
import com.github.io.MateusHCandido.account_service.infra.util.TransactionStatusConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long transactionId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_account_number", referencedColumnName = "accountNumber")
    private AccountEntity numberAccountSender;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_account_number", referencedColumnName = "accountNumber")
    private AccountEntity numberAccountReceiver;

    @Column(scale = 2)
    private BigDecimal transferredValue;

    @Convert(converter = TransactionStatusConverter.class)
    private TransactionStatus transactionStatus;

    private String transactionMessage;
    private LocalDate timestampTransaction;
}
