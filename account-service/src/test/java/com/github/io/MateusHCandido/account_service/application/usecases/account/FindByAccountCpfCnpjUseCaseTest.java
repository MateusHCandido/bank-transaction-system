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

class FindByAccountCpfCnpjUseCaseTest {

    @Mock
    private AppAccountRepository repository;

    @InjectMocks
    private FindByAccountCpfCnpjUseCase useCase;

    private Account account;

    private String validCpf;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        account = new Account(123L, BigDecimal.ZERO, AccountType.NATURAL_PERSON
                , "test", "12345678910", "teste@teste.com", 123L);

        validCpf = "12345678910";
    }

    @Test
    @DisplayName("The method will find a account when receiver the cpf parameter")
    void shouldFindAccountWithCpfParameter(){
        when(repository.findByAccountCpfCnpj(validCpf)).thenReturn(Optional.of(account));

        Account result = useCase.findByAccountCpfCnpjUseCase(validCpf);

        assertNotNull(result);
        assertTrue(validCpf.equals(result.getAccountCpfCnpj()));
        verify(repository, times(1)).findByAccountCpfCnpj(validCpf);
    }


    @Test
    @DisplayName("An exception will be thrown because the cpf or cnpj were not found")
    void shouldThrowExceptionWhenCallingMethodForFindAccountByCpfCnpj(){
        String cpfNotFound = "12345612345";
        String cnpjNotFound = "12345612345613";

        assertThrows(BankAccountNotFoundException.class, () -> {
            useCase.findByAccountCpfCnpjUseCase(cpfNotFound);
        });

        assertThrows(BankAccountNotFoundException.class, () -> {
            useCase.findByAccountCpfCnpjUseCase(cnpjNotFound);
        });
    }

}