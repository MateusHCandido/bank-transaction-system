package com.github.io.MateusHCandido.account_service.infra.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.io.MateusHCandido.account_service.domain.enums.AccountType;
import com.github.io.MateusHCandido.account_service.infra.util.AccountTypeConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;
    @Column(scale = 2)
    private BigDecimal accountBalance;
    @Convert(converter = AccountTypeConverter.class)
    private AccountType accountType;
    private String accountName;
    @Column(unique = true)
    private String accountCpfCnpj;
    @Column(unique = true)
    private String accountEmail;
    private Long bankCode;

    @JsonIgnore
    @OneToMany(mappedBy = "numberAccountSender")
    private List<TransactionEntity> sentTransactions;

    @JsonIgnore
    @OneToMany(mappedBy = "numberAccountReceiver")
    private List<TransactionEntity> receivedTransactions;

    public AccountEntity(Long accountNumber, BigDecimal accountBalance, AccountType accountType, String accountName
                        , String accountCpfCnpj, String accountEmail, Long bankCode) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountType = accountType;
        this.accountName = accountName;
        this.accountCpfCnpj = accountCpfCnpj;
        this.accountEmail = accountEmail;
        this.bankCode = bankCode;
    }

    public AccountEntity(Long accountNumber, BigDecimal accountBalance, AccountType accountType, String accountName,
                         String accountCpfCnpj, String accountEmail, Long bankCode,
                         List<TransactionEntity> sentTransactions, List<TransactionEntity> receivedTransactions) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountType = accountType;
        this.accountName = accountName;
        this.accountCpfCnpj = accountCpfCnpj;
        this.accountEmail = accountEmail;
        this.bankCode = bankCode;
        this.sentTransactions = sentTransactions;
        this.receivedTransactions = receivedTransactions;
    }
}
