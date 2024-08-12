package com.github.io.MateusHCandido.account_service.configuration;

import com.github.io.MateusHCandido.account_service.application.gateways.AppAccountRepository;
import com.github.io.MateusHCandido.account_service.application.gateways.AppTransactionRepository;
import com.github.io.MateusHCandido.account_service.application.usecases.transaction.TransferUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionConfig {


    @Bean
    TransferUseCase transferUseCase( AppTransactionRepository transactionRepository, AppAccountRepository accountRepository){
        return new TransferUseCase(transactionRepository, accountRepository);
    }

}
