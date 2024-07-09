package com.github.io.MateusHCandido.bank_service_10.core.config;


import com.github.io.MateusHCandido.bank_service_10.core.gateway.BankUseCaseRepository;
import com.github.io.MateusHCandido.bank_service_10.core.usecase.BankUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BankUseCaseConfigTest.Config.class})
class BankUseCaseConfigTest {


    @Configuration
    static class Config{
        @MockBean
        private BankUseCaseRepository repository;

        @Bean
        public BankUseCase bankUseCase(){
            return new BankUseCase((repository));
        }
    }

    @Autowired
    private BankUseCase bankUseCase;

    @Test
    void testBankUseCaseBean() {
        assertNotNull(bankUseCase);
    }
}