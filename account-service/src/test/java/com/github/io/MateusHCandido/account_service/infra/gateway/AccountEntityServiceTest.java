package com.github.io.MateusHCandido.account_service.infra.gateway;

import com.github.io.MateusHCandido.account_service.application.usecases.account.exception.BankAccountNotFoundException;
import com.github.io.MateusHCandido.account_service.application.usecases.account.exception.EmailAlreadyExistsException;
import com.github.io.MateusHCandido.account_service.configuration.AccountMapper;
import com.github.io.MateusHCandido.account_service.domain.Account;
import com.github.io.MateusHCandido.account_service.domain.enums.AccountType;
import com.github.io.MateusHCandido.account_service.domain.exception.InvalidEmailException;
import com.github.io.MateusHCandido.account_service.infra.persistence.AccountEntity;
import com.github.io.MateusHCandido.account_service.infra.gateway.exception.CpfCnpjAlreadyExistException;
import com.github.io.MateusHCandido.account_service.infra.persistence.repository.AccountEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountEntityServiceTest {

    @Mock
    private AccountEntityRepository repository;

    @Mock
    private AccountMapper mapper;

    @InjectMocks
    private AccountEntityService service;

    private Account account;

    private AccountEntity entity;

    private String validEmail;

    private AccountEntity entityWithNewEmail;

    @BeforeEach
    void setUp(){

        validEmail = "valid_email@teste.com";

        account = new Account(123L, BigDecimal.ZERO, AccountType.NATURAL_PERSON
                , "test", "12345678910", "teste@teste.com", 123L);

        entity = new AccountEntity(123L, BigDecimal.ZERO, AccountType.NATURAL_PERSON
                , "test", "12345678910", "teste@teste.com", 123L);

        entityWithNewEmail = new AccountEntity(123L, BigDecimal.ZERO, AccountType.NATURAL_PERSON,
                "test", "12345678910", validEmail, 123L);
    }

    @Test
    void shouldThrowExceptionWhenCpfCnpjAlreadyExists() {
        given(repository.existsByAccountCpfCnpj(account.getAccountCpfCnpj())).willReturn(true);

        CpfCnpjAlreadyExistException exception = assertThrows(CpfCnpjAlreadyExistException.class, () -> {
            service.saveAccount(account);
        });

        assertEquals("CPF/CNPJ already exist", exception.getMessage());

        verify(repository, times(0)).save(entity);
    }


    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        given(repository.existsByAccountEmail(account.getAccountEmail())).willReturn(true);

        EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class, () -> {
            service.saveAccount(account);
        });

        assertEquals("Email already exist", exception.getMessage());

        verify(repository, times(0)).save(entity);
    }

    @Test
    void shouldReturnTheAccountWhenCpfCnpjExist(){
        when(repository.findByAccountCpfCnpj(account.getAccountCpfCnpj())).thenReturn(Optional.of(entity));
        when(service.findByAccountCpfCnpj(account.getAccountCpfCnpj())).thenReturn(Optional.of(account));

        Optional<Account> result = service.findByAccountCpfCnpj(account.getAccountCpfCnpj());

        assertNotNull(result);
        assertEquals(result.get().getAccountCpfCnpj(), entity.getAccountCpfCnpj());
        verify(repository, times(2)).findByAccountCpfCnpj(account.getAccountCpfCnpj());
    }

    @Test
    @DisplayName("Should throw exception because account email not exists in database")
    void shouldThrowExceptionWhenNotFoundAccountByAccountEmail(){
        given(repository.findByAccountEmail(account.getAccountEmail())).willReturn(Optional.empty());

        BankAccountNotFoundException exception = assertThrows(BankAccountNotFoundException.class, () -> {
            service.findByAccountEmail(account.getAccountEmail());
        });

        assertEquals("Account not found", exception.getMessage());

        verify(repository, times(0)).save(entity);
    }

    @Test
    void shouldReturnTheAccountWhenEmailExist(){
        when(repository.findByAccountEmail(account.getAccountEmail())).thenReturn(Optional.of(entity));
        when(service.findByAccountEmail(account.getAccountEmail())).thenReturn(Optional.of(account));

        Optional<Account> result = service.findByAccountEmail(account.getAccountEmail());

        assertNotNull(result);
        assertEquals(result.get().getAccountEmail(), entity.getAccountEmail());
    }

    @Test
    @DisplayName("Should throw exception because account number not exists in database")
    void shouldThrowExceptionWhenNotFoundAccountByAccountNumber(){
        given(repository.findById(account.getAccountNumber())).willReturn(Optional.empty());

        BankAccountNotFoundException exception = assertThrows(BankAccountNotFoundException.class, () -> {
            service.findByAccountNumber(account.getAccountNumber());
        });

        assertEquals("Account not found", exception.getMessage());
    }

    @Test
    void shouldReturnTheAccountWhenAccountNumberExist() {
        when(repository.findById(account.getAccountNumber())).thenReturn(Optional.of(entity));
        when(service.findByAccountNumber(account.getAccountNumber())).thenReturn(Optional.of(account));

        Optional<Account> result = service.findByAccountNumber(account.getAccountNumber());

        assertNotNull(result);
        assertEquals(result.get().getAccountNumber(), entity.getAccountNumber());
    }
}