package com.github.io.MateusHCandido.bank_service_10.config;

import com.github.io.MateusHCandido.bank_service_10.core.Bank;
import com.github.io.MateusHCandido.bank_service_10.infra.controller.dto.BankDtoRequest;
import com.github.io.MateusHCandido.bank_service_10.infra.controller.dto.BankDtoResponse;
import com.github.io.MateusHCandido.bank_service_10.infra.persistence.BankEntity;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        
        modelMapper.addConverter(new AbstractConverter<BankDtoRequest, BankEntity>() {
            @Override
            protected BankEntity convert(BankDtoRequest source) {
                return new BankEntity(source.getBankCode(), source.getBankName());
            }
        });

        modelMapper.addConverter(new AbstractConverter<BankEntity, BankDtoResponse>() {
            @Override
            protected BankDtoResponse convert(BankEntity source) {
                return new BankDtoResponse(source.getBankCode(), source.getBankName());
            }
        });

        modelMapper.addConverter(new AbstractConverter<BankEntity, Bank>() {
            @Override
            protected Bank convert(BankEntity source) {
                return new Bank(source.getBankCode(), source.getBankName());
            }
        });

        modelMapper.addConverter(new AbstractConverter<Bank, BankEntity>() {
            @Override
            protected BankEntity convert(Bank source) {
                return new BankEntity(source.getBankCode(), source.getBankName());
            }
        });

        return modelMapper;
    }
}
