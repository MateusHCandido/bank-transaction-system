package com.github.io.MateusHCandido.bank_service_10.core.usecase;

import com.github.io.MateusHCandido.bank_service_10.core.Bank;
import com.github.io.MateusHCandido.bank_service_10.core.exception.BankNameIsBlankException;
import com.github.io.MateusHCandido.bank_service_10.core.exception.BankNotFoundException;
import com.github.io.MateusHCandido.bank_service_10.core.gateway.BankUseCaseRepository;

import java.util.List;
import java.util.Optional;


public class BankUseCase  {

    private final BankUseCaseRepository repository;


    public BankUseCase(BankUseCaseRepository repository) {
        this.repository = repository;
    }


    public String create(int code, String name) {
        try{
           repository.create( new Bank(code, name) );
           return "Bank created";
        }catch (Exception e ){
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Bank> listBanks(){
        return repository.listBanks();
    }

    public List<Bank> listBanksByName(String bankName) {
        if (bankName.isBlank()){ throw new BankNameIsBlankException("The bank name is blank"); }

        return repository.findByBankName(bankName);
    }

    public Bank findBankByBankCode(int bankCode) {
        return repository.findByBankCode(bankCode);
    }
}
