package com.github.io.MateusHCandido.bank_service_10.core;


import com.github.io.MateusHCandido.bank_service_10.core.exception.BankCodeInvalidExcEptIon;
import com.github.io.MateusHCandido.bank_service_10.core.exception.BankNameIsBlankException;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Bank {

    private int bankCode;

    private String bankName;

    public Bank(int bankCode, String bankName) {
        this.bankCode = checkBankCode(bankCode);
        this.bankName = checkBankName(bankName);
    }

    private int checkBankCode(int code) {
        if(code > 0){
            return code;
        }
        throw new BankCodeInvalidExcEptIon("bank code contains value equal to or below zero");
    }

    protected String checkBankName(String name){
        boolean nameIsEmpty = name.isEmpty();

        if (nameIsEmpty){
            throw new BankNameIsBlankException("The bank name is blank");
        }
        return name.trim();
    }
}
