package com.github.io.MateusHCandido.account_service.application.usecases.account;

import com.github.io.MateusHCandido.account_service.application.gateways.AppAccountRepository;
import com.github.io.MateusHCandido.account_service.application.usecases.account.exception.BankAccountNotFoundException;
import com.github.io.MateusHCandido.account_service.domain.Account;
import com.github.io.MateusHCandido.account_service.domain.enums.AccountType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindByAccountNumberUseCaseTest {

    @Mock
    private AppAccountRepository repository;

    @InjectMocks
    private FindByAccountNumberUseCase useCase;

    private Long accountNumber;

    private Account account;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        account = new Account(123L, BigDecimal.ZERO, AccountType.NATURAL_PERSON
                , "test", "12345678910", "teste@teste.com", 123L);

        accountNumber = 123L;
    }

    @Test
    @DisplayName("The method will find a account when receiver the number account parameter")
    void shouldFindTheAccountWhenCallingTheAccountNumberParameter(){
        when(repository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));

        Account result = useCase.findByAccountNumberUseCase(accountNumber);

        assertNotNull(result);
        assertEquals(123L, result.getAccountNumber());
        verify(repository,times(1)).findByAccountNumber(accountNumber);
    }

    //conta não localizada
    @Test
    @DisplayName("An exception will be thrown because the number account were not found")
    void shouldThrowExceptionWhenCallingMethodForFindAccountByNumber(){
        assertThrows(BankAccountNotFoundException.class, () -> {
            useCase.findByAccountNumberUseCase(accountNumber);
        });
    }
}