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

class UpdateEmailAccountUseCaseTest {

    @Mock
    private AppAccountRepository repository;
    @InjectMocks
    private UpdateEmailAccountUseCase useCase;

    Account account;

    String newEmail;

    String previousEmail;

    Long accountNumber;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        accountNumber = 123L;
        newEmail = "test_with_success@teste.com";
        previousEmail = "teste@teste.com";

        account = new Account(accountNumber, BigDecimal.ZERO, AccountType.NATURAL_PERSON
                , "test", "12345678910", previousEmail, 123L);
    }

    @Test
    @DisplayName("The test must allow the account email to be changed")
    void shouldAdjustEmailInAccount(){
        when(repository.updateEmailAccount(accountNumber, newEmail)).thenReturn(account);

        Account result =  useCase.updateEmailAccountUseCase(accountNumber, newEmail);

        assertNotNull(result);
        verify(repository,times(1)).updateEmailAccount(accountNumber, newEmail);
    }


}