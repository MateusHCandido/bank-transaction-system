package com.github.io.MateusHCandido.account_service.application.usecases.transaction;

import com.github.io.MateusHCandido.account_service.application.gateways.AppAccountRepository;
import com.github.io.MateusHCandido.account_service.application.gateways.AppTransactionRepository;
import com.github.io.MateusHCandido.account_service.application.usecases.account.exception.BankAccountNotFoundException;
import com.github.io.MateusHCandido.account_service.application.usecases.transaction.exception.InsufficientAccountBalanceException;
import com.github.io.MateusHCandido.account_service.application.usecases.transaction.exception.SenderAndReceiverAccountDuplicateException;
import com.github.io.MateusHCandido.account_service.application.usecases.transaction.exception.TransactionFailureException;
import com.github.io.MateusHCandido.account_service.domain.Account;
import com.github.io.MateusHCandido.account_service.domain.Transaction;
import com.github.io.MateusHCandido.account_service.domain.enums.TransactionStatus;

import java.math.BigDecimal;

public class TransferUseCase {
    private final AppTransactionRepository transactionRepository;
    private final AppAccountRepository accountRepository;


    public TransferUseCase(AppTransactionRepository transactionRepository, AppAccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }


    public Transaction transfer(Long numberAccountSender, Long numberAccountReceiver, BigDecimal transferredValue){
        Account accountSender = validateAccountExistence(numberAccountSender);

        checkIfAccountsAreDuplicated(numberAccountSender, numberAccountReceiver);
        hasSufficientBalance(accountSender, transferredValue);
        checkTransferValue(transferredValue);

        return transactionRepository.transfer(new Transaction(numberAccountSender, numberAccountReceiver,
                                                    transferredValue, TransactionStatus.SUCCESSFUL, "transaction completed with success"));
    }

    protected Account validateAccountExistence(Long numberAccount) {
        return accountRepository.findByAccountNumber(numberAccount).orElseThrow(() -> new BankAccountNotFoundException("account not found"));
    }
    protected static void checkTransferValue(BigDecimal value){
        boolean isNegative = value.signum() == -1;
        if(isNegative) throw new TransactionFailureException("The transferred value is negative");
    }
    protected static void checkIfAccountsAreDuplicated(Long numberAccountSender, Long numberAccountReceiver) {
        if (numberAccountSender.equals(numberAccountReceiver))
            throw new SenderAndReceiverAccountDuplicateException("The number accounts are equals");
    }
    protected static void hasSufficientBalance(Account accountSender, BigDecimal transferredValue) {
        boolean isBalanceSufficient = accountSender.getAccountBalance().compareTo(transferredValue) >= 0;
        if(!isBalanceSufficient)
            throw new InsufficientAccountBalanceException("The balance for the transaction is insufficient");
    }
}
