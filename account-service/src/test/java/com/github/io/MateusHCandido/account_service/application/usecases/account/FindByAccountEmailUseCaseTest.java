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

class FindByAccountEmailUseCaseTest {

    @Mock
    private AppAccountRepository repository;

    @InjectMocks
    private FindByAccountEmailUseCase useCase;

    private Account account;
    private String validEmail;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        account = new Account(123L, BigDecimal.ZERO, AccountType.NATURAL_PERSON
                , "test", "12345678910", "teste@teste.com", 123L);

        validEmail = "teste@test.com";
    }

    //conta encontrada
    @Test
    @DisplayName("The test will search for an email account and it will be found successfully")
    void shouldReturnAccountWhenEmailParameterReceiver(){
        when(repository.findByAccountEmail(validEmail)).thenReturn(Optional.of(account));

        Account result = useCase.findByAccountEmailUseCase(validEmail);

        assertNotNull(result);
        assertTrue(result.getAccountCpfCnpj().equals("12345678910"));
        verify(repository, times(1)).findByAccountEmail(validEmail);
    }

    //conta não encontrada

    @Test
    void shouldThrowAnExceptionBecauseTheAccountWillBeNotFound(){
        String emailNotFound = "teste123@teste.com";

        assertThrows(BankAccountNotFoundException.class, () -> {
            useCase.findByAccountEmailUseCase(emailNotFound);
        });
    }


}