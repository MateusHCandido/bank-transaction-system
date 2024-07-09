package com.github.io.MateusHCandido.bank_service_10.core.gateway;

import com.github.io.MateusHCandido.bank_service_10.core.Bank;

import java.util.List;


public interface BankUseCaseRepository {

    void create(Bank bank);

    List<Bank> listBanks();

    List<Bank> findByBankName(String bankName);

    Bank findByBankCode(int bankCode);
}
