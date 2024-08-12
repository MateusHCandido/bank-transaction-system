package com.github.io.MateusHCandido.account_service.application.usecases.account;

import com.github.io.MateusHCandido.account_service.application.gateways.AppAccountRepository;
import com.github.io.MateusHCandido.account_service.domain.Account;
import com.github.io.MateusHCandido.account_service.domain.enums.AccountType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaveAccountUseCaseTest {

    @Mock
    private AppAccountRepository repository;

    @InjectMocks
    private SaveAccountUseCase useCase;

    Account account;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        account = new Account(123L, BigDecimal.ZERO, AccountType.NATURAL_PERSON
                , "test", "12345678910", "teste@teste.com", 123L);}

    @Test
    @DisplayName("The test will be the account")
    void shouldSaveTheAccount(){
        when(repository.saveAccount(account)).thenReturn(account);

        Account result = useCase.saveAccountUseCase(account);

        assertNotNull(result);
        verify(repository, times(1)).saveAccount(account);
    }
}