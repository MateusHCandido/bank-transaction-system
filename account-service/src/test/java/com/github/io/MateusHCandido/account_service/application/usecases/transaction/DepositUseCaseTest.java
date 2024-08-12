package com.github.io.MateusHCandido.account_service.application.usecases.transaction;


import com.github.io.MateusHCandido.account_service.application.gateways.AppAccountRepository;
import com.github.io.MateusHCandido.account_service.application.gateways.AppTransactionRepository;
import com.github.io.MateusHCandido.account_service.application.usecases.account.exception.BankAccountNotFoundException;
import com.github.io.MateusHCandido.account_service.application.usecases.transaction.exception.SenderAndReceiverAccountDuplicateException;
import com.github.io.MateusHCandido.account_service.application.usecases.transaction.exception.TransactionFailureException;
import com.github.io.MateusHCandido.account_service.domain.Account;
import com.github.io.MateusHCandido.account_service.domain.Transaction;
import com.github.io.MateusHCandido.account_service.domain.enums.AccountType;
import com.github.io.MateusHCandido.account_service.domain.enums.TransactionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransferUseCaseTest {

    @Mock
    private AppAccountRepository accountRepository;

    @Mock
    private AppTransactionRepository transactionRepository;

    @InjectMocks
    private TransferUseCase transferUseCase;

    private Account accountSender;

    private Account accountReceiver;

    private Account accountWithInsufficientBalance;

    private Transaction failedTransaction;

    private BigDecimal senderInitialBalance;

    private BigDecimal receiverInitialBalance;

    private Long numberAccountSender;

    private Long numberAccountReceiver;

    private BigDecimal valueForTransfer;

    private Transaction successfulTransaction;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        numberAccountSender = 123L;
        numberAccountReceiver = 112L;
        valueForTransfer = new BigDecimal("100.00");

        accountSender = new Account(numberAccountSender, new BigDecimal("300.00"), AccountType.LEGAL_PERSON
                , "sender", "12345612365412", "sender@sender.com", 321L);

        accountReceiver = new Account(numberAccountReceiver, new BigDecimal("200.0"), AccountType.LEGAL_PERSON
                , "receiver", "12345612365411", "receiver@receiver.com", 321L);

        accountWithInsufficientBalance = new Account(numberAccountSender, new BigDecimal("50.00"), AccountType.LEGAL_PERSON
                , "sender", "12345612365412", "sender@sender.com", 321L);

        successfulTransaction = new Transaction(numberAccountSender, numberAccountReceiver, valueForTransfer, TransactionStatus.SUCCESSFUL, "");

        failedTransaction = new Transaction(numberAccountSender, numberAccountReceiver, valueForTransfer, TransactionStatus.FAILURE, "The balance for the transaction is insufficient");

        senderInitialBalance = accountSender.getAccountBalance();
        receiverInitialBalance = accountReceiver.getAccountBalance();
    }

    @Test
    void shouldPerformTheTransfer(){
        when(accountRepository.findByAccountNumber(numberAccountSender)).thenReturn(Optional.of(accountSender));
        when(transactionRepository.transfer(any(Transaction.class))).thenReturn(successfulTransaction);

        Transaction transaction = transferUseCase.transfer(numberAccountSender, numberAccountReceiver, valueForTransfer);

        assertNotNull(transaction);

        verify(accountRepository, times(1)).findByAccountNumber(numberAccountSender);
    }

    @Test
    void shouldThrowExceptionBecauseNotFoundSenderOrReceiveAccount(){
        BankAccountNotFoundException exception = assertThrows(BankAccountNotFoundException.class, () -> {
            transferUseCase.transfer(numberAccountSender, numberAccountReceiver, valueForTransfer);
        });

        assertEquals("account not found", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionBecauseTheValueForTransferIsNegative(){
        when(accountRepository.findByAccountNumber(numberAccountSender)).thenReturn(Optional.of(accountSender));
        when(accountRepository.findByAccountNumber(numberAccountReceiver)).thenReturn(Optional.of(accountReceiver));

        TransactionFailureException exception = assertThrows(TransactionFailureException.class, () -> {
            transferUseCase.transfer(numberAccountSender, numberAccountReceiver, new BigDecimal(-20));
        });

        assertEquals("The transferred value is negative", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionBecauseTheBalanceAccountIsInsufficientForTransfer(){
        when(accountRepository.findByAccountNumber(numberAccountSender)).thenReturn(Optional.of(accountSender));
        when(accountRepository.findByAccountNumber(numberAccountReceiver)).thenReturn(Optional.of(accountReceiver));
        when(transactionRepository.transfer(any(Transaction.class))).thenReturn(failedTransaction);

        Transaction transaction = transferUseCase.transfer(numberAccountSender, numberAccountReceiver, valueForTransfer);

        assertNotNull(transaction);
    }

    @Test
    void shouldThrowExceptionBecauseSenderAndReceiverAccountNumberAreEqual(){
        when(accountRepository.findByAccountNumber(numberAccountSender)).thenReturn(Optional.of(accountSender));

        SenderAndReceiverAccountDuplicateException exception =
                assertThrows((SenderAndReceiverAccountDuplicateException.class), () -> {
                    transferUseCase.transfer(numberAccountSender, numberAccountSender, valueForTransfer);
                });

        assertEquals("The number accounts are equals", exception.getMessage());

    }

}