package com.github.io.MateusHCandido.bank_service_10.core.usecase;

import com.github.io.MateusHCandido.bank_service_10.core.exception.BankNameIsBlankException;
import com.github.io.MateusHCandido.bank_service_10.core.gateway.BankUseCaseRepository;
import com.github.io.MateusHCandido.bank_service_10.core.Bank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class BankUseCaseTest {

    @Mock
    private BankUseCaseRepository repository;

    @InjectMocks
    private BankUseCase useCase;



    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should return a message of success when call method for create a bank")
    public void createBankCase01(){
        String bankName = "bank test";
        int bankCode = 123;

        useCase.create(bankCode, bankName);

        verify(repository, times(1)).create(new Bank(bankCode, bankName));
    }

    @Test
    @DisplayName("should throw exception when the bank name parameter are not correct")
    public void createBankCase02(){
        String bankName = "";
        int bankCode = 123;

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> useCase.create(bankCode, bankName));
        assertTrue(runtimeException.getMessage().contains("The bank name is blank"));
    }

    @Test
    @DisplayName("should throw exception when the bank code parameter are not correct")
    public void createBankCase03(){
        String bankName = "Test";
        int bankCode = 0;

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> useCase.create(bankCode, bankName));
        assertTrue(runtimeException.getMessage().contains("bank code contains value equal to or below zero"));
    }


    @Test
    @DisplayName("Should return a bank list")
    public void listBanksCase01(){
        List<Bank> banks = new ArrayList<>();

        banks.add(new Bank(1, "Test 1"));
        banks.add(new Bank(2, "Test 2"));
        banks.add(new Bank(3, "Test 3"));
        banks.add(new Bank(4, "Test 4"));

        when(useCase.listBanks()).thenReturn(banks);

        List<Bank> result = useCase.listBanks();

        Assertions.assertEquals(result.size(), 4);
    }


    @Test
    @DisplayName("Should return a bank list with two banks registered when call method for find banks by name")
    public void listBanksCase02(){
        List<Bank> banks = new ArrayList<>();
        String bankName = "Test";

        banks.add(new Bank(1, "Test"));
        banks.add(new Bank(2, "Test"));

        when(useCase.listBanksByName(bankName)).thenReturn(banks);

        List<Bank> result = useCase.listBanksByName(bankName);

        Assertions.assertEquals(result.size(), 2);
    }

    @Test
    @DisplayName("Should return a bank list with one bank registered when call method for find banks by name")
    public void listBanksCase03(){
        List<Bank> banks = new ArrayList<>();
        String bankName = "Test";

        banks.add(new Bank(1, "Test"));

        when(useCase.listBanksByName(bankName)).thenReturn(banks);

        List<Bank> result = useCase.listBanksByName(bankName);

        Assertions.assertEquals(result.size(), 1);
    }

    @Test
    @DisplayName("Should throw exception when call method for find banks by name and the parameter is blank")
    public void listBanksCase04(){
        List<Bank> banks = new ArrayList<>();
        String bankName = "";

        banks.add(new Bank(1, "Test"));

        RuntimeException runtimeException = assertThrows(BankNameIsBlankException.class, () -> useCase.listBanksByName(bankName));
        assertTrue(runtimeException.getMessage().contains("The bank name is blank"));
    }

    @Test
    @DisplayName("Should return a bank when call method for search a bank by code")
    public void findBankCase01(){
        Bank bankRegistered = new Bank(123,"Test");
        int bankCode = 123;

        when(useCase.findBankByBankCode(bankCode)).thenReturn(bankRegistered);

        Bank result = useCase.findBankByBankCode(bankCode);

        Assertions.assertEquals(result.getBankCode(), 123);
        Assertions.assertNotNull(result);
    }
}
