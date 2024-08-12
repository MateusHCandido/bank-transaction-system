package com.github.io.MateusHCandido.account_service.application.gateways;

import com.github.io.MateusHCandido.account_service.domain.Transaction;


public interface AppTransactionRepository {

    Transaction transfer(Transaction transaction);
}
