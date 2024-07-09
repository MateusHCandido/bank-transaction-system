package com.github.io.MateusHCandido.bank_service_10.infra.controller;

import com.github.io.MateusHCandido.bank_service_10.core.Bank;
import com.github.io.MateusHCandido.bank_service_10.core.usecase.BankUseCase;
import com.github.io.MateusHCandido.bank_service_10.infra.controller.dto.BankDtoRequest;
import com.github.io.MateusHCandido.bank_service_10.infra.controller.dto.BankDtoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/banks")
public class BankController {

    @Autowired
    private BankUseCase useCase;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createBank(@Valid @RequestBody BankDtoRequest dto){
        return useCase.create(dto.getBankCode(), dto.getBankName());
    }

    @GetMapping("/list-banks")
    @ResponseStatus(HttpStatus.OK)
    public List<BankDtoResponse> listBanks(){
        return useCase.listBanks().stream()
                .map(bank -> modelMapper.map(bank, BankDtoResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/list-banks/by-name/{bankName}")
    @ResponseStatus(HttpStatus.OK)
    public List<BankDtoResponse> listBanksByBankName(@PathVariable String bankName){
        return useCase.listBanksByName(bankName).stream()
                .map(bank -> modelMapper.map(bank, BankDtoResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/find-by-code/{bankCode}")
    public BankDtoResponse findBankByBankCode(@PathVariable Integer bankCode){
        Bank entity = useCase.findBankByBankCode(bankCode);
        return modelMapper.map(entity, BankDtoResponse.class);
    }
}
