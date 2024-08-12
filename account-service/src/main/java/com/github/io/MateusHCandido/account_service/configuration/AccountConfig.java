package com.github.io.MateusHCandido.account_service.configuration;

import com.github.io.MateusHCandido.account_service.application.gateways.AppAccountRepository;
import com.github.io.MateusHCandido.account_service.application.usecases.account.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountConfig {

    @Bean
    FindByAccountEmailUseCase findByAccountEmailUseCase(AppAccountRepository accountRepository){
        return new FindByAccountEmailUseCase(accountRepository);
    }
    @Bean
    FindByAccountNumberUseCase findByAccountNumberUseCase(AppAccountRepository accountRepository){
        return new FindByAccountNumberUseCase(accountRepository);
    }
    @Bean
    FindByAccountCpfCnpjUseCase findByAccountCpfCnpjUseCase(AppAccountRepository accountRepository){
        return new FindByAccountCpfCnpjUseCase(accountRepository);
    }
    @Bean
    SaveAccountUseCase saveAccountUseCase(AppAccountRepository accountRepository){
        return new SaveAccountUseCase(accountRepository);
    }
    @Bean
    UpdateEmailAccountUseCase updateEmailAccountUseCase(AppAccountRepository accountRepository){
        return new UpdateEmailAccountUseCase(accountRepository);
    }
}
