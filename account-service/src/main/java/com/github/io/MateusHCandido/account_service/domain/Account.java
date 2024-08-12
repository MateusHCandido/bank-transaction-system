package com.github.io.MateusHCandido.account_service.domain;

import com.github.io.MateusHCandido.account_service.domain.enums.AccountType;
import com.github.io.MateusHCandido.account_service.domain.exception.AccountTypeException;
import com.github.io.MateusHCandido.account_service.domain.exception.InvalidCnpjException;
import com.github.io.MateusHCandido.account_service.domain.exception.InvalidCpfException;
import com.github.io.MateusHCandido.account_service.domain.exception.InvalidEmailException;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class Account {

    private Long accountNumber;
    private BigDecimal accountBalance;
    private AccountType accountType;
    private String accountName;
    private String accountCpfCnpj;
    private String accountEmail;
    private Long bankCode;

    public Account(){}

    public Account(Long accountNumber, BigDecimal accountBalance, AccountType accountType,
                   String accountName, String accountCpfCnpj, String accountEmail, Long bankCode) {
        this.accountType = isValidAccountType(accountType);
        this.accountCpfCnpj = isValidCpfCnpj(accountCpfCnpj, accountType);
        this.accountEmail = isValidEmail(accountEmail);
        this.accountBalance = isValidAccountBalance(accountBalance);

        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.accountName = accountName;
        this.bankCode = bankCode;
    }


    public void updateEmail(String accountEmail) {
        this.accountEmail = isValidEmail(accountEmail);
    }
    protected BigDecimal isValidAccountBalance(BigDecimal accountBalance) {
        try{
            return  accountBalance != null ? accountBalance : BigDecimal.ZERO;
        }catch (NullPointerException e){
            return BigDecimal.ZERO;
        }
    }

    protected AccountType isValidAccountType(AccountType accountType) {
        if (accountType == null) {
            throw new AccountTypeException("AccountType cannot be null");
        }
        return accountType;
    }

    protected String isValidCpfCnpj(String cpfCnpj, AccountType accountType){
        switch (accountType) {
            case NATURAL_PERSON:
                if (cpfCnpj.matches("\\d{11}")) break;
                throw new InvalidCpfException("the CPF not contains 11 digits");
            case LEGAL_PERSON:
                if (cpfCnpj.matches("\\d{14}")) break;
                throw new InvalidCnpjException("the CNPJ not contains 14 digits");
        }

        return cpfCnpj;
    }

    public void transferValue(BigDecimal valueForTransfer){
        this.accountBalance = this.accountBalance.subtract(valueForTransfer);
    }

    public void receiveValue(BigDecimal transferredValue){
        this.accountBalance = this.accountBalance.add(transferredValue);
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    public Long getBankCode() {
        return bankCode;
    }

    protected static String isValidEmail(String email) {
        try{
            String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
            Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
            String domain = email.substring(email.indexOf('@') + 1);

            boolean emailIsEmpty = email.equals("");
            boolean emailPatternIsInvalid = !EMAIL_PATTERN.matcher(email).matches();
            boolean emailContainsDoubleDots = domain.contains("..");

            if (emailIsEmpty || emailPatternIsInvalid || emailContainsDoubleDots){
                throw new InvalidEmailException("The formatting of the email is not correct, please check");
            }
        }catch (NullPointerException exception){
            throw new InvalidEmailException("The email is null");
        }

        return email;
    }

    public String getAccountCpfCnpj() {
        return accountCpfCnpj;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountCpfCnpj(String accountCpfCnpj) {
        this.accountCpfCnpj = accountCpfCnpj;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public void setBankCode(Long bankCode) {
        this.bankCode = bankCode;
    }
}
