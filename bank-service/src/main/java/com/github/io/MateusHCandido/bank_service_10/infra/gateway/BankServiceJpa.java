package com.github.io.MateusHCandido.bank_service_10.infra.gateway;

import com.github.io.MateusHCandido.bank_service_10.core.Bank;
import com.github.io.MateusHCandido.bank_service_10.core.exception.BankNotFoundException;
import com.github.io.MateusHCandido.bank_service_10.core.gateway.BankUseCaseRepository;
import com.github.io.MateusHCandido.bank_service_10.infra.controller.dto.BankDtoRequest;
import com.github.io.MateusHCandido.bank_service_10.infra.controller.dto.BankDtoResponse;
import com.github.io.MateusHCandido.bank_service_10.infra.gateway.exception.BankCriationException;
import com.github.io.MateusHCandido.bank_service_10.infra.gateway.exception.ListIsEmptyException;
import com.github.io.MateusHCandido.bank_service_10.infra.persistence.BankEntity;
import com.github.io.MateusHCandido.bank_service_10.infra.persistence.BankEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankServiceJpa implements BankUseCaseRepository {

    private final BankEntityRepository repository;
    private final ModelMapper modelMapper;

    public BankServiceJpa(BankEntityRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }



    @Override
    public void create(Bank bank) {
        try {
            BankEntity entity = modelMapper.map(bank, BankEntity.class);
            repository.save(entity);

        }catch (RuntimeException e){
            throw new BankCriationException(e.getMessage());
        }
    }

    @Override
    public List<Bank> listBanks(){
        List<Bank> response = repository.findAll()
                .stream()
                .map(bankResponse -> modelMapper.map(bankResponse, Bank.class))
                .collect(Collectors.toList());

        if (response.isEmpty()){
            throw new ListIsEmptyException("The list is empty");
        }

        return response;
    }

    @Override
    public List<Bank> findByBankName(String bankName){
        List<Bank> response = repository.findByBankName(bankName)
                .stream()
                .map(bankResponse -> modelMapper.map(bankResponse, Bank.class))
                .collect(Collectors.toList());

        if (response.isEmpty()){
            throw new ListIsEmptyException("The list is empty");
        }

        return response;
    }

    @Override
    public Bank findByBankCode(int bankCode) {
        Optional<BankEntity> bank = Optional.ofNullable(repository.findByBankCode(bankCode)
                .orElseThrow(() -> new BankNotFoundException("Bank not found")));

        return modelMapper.map(bank.get(), Bank.class);
    }



}
