package com.github.io.MateusHCandido.bank_service_10.config;

import com.github.io.MateusHCandido.bank_service_10.core.gateway.BankUseCaseRepository;
import com.github.io.MateusHCandido.bank_service_10.core.usecase.BankUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankUseCaseConfig {

    @Bean
    BankUseCase bankUseCase(BankUseCaseRepository bankUseCaseRepository){
        return new BankUseCase(bankUseCaseRepository);
    }

}
