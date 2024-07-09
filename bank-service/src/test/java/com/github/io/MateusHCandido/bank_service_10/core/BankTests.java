package com.github.io.MateusHCandido.bank_service_10.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankTests {

    @Test
    @DisplayName("Should create a bank entity")
    public void createDomainBankCase01(){
        Bank bank = new Bank(123, "Test");

        Assertions.assertEquals(bank.getBankCode(), 123);
        Assertions.assertTrue(bank.getBankName().equals("Test"));
        Assertions.assertNotNull(bank);
    }


    @Test
    @DisplayName("Should not create a bank because the bank name parameter is blank")
    public void createDomainBankCase02(){
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> new Bank(123, ""));
        Assertions.assertTrue(runtimeException.getMessage().contains("The bank name is blank"));
    }
}
