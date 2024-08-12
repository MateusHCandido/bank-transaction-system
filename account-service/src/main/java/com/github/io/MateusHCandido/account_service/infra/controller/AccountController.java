package com.github.io.MateusHCandido.account_service.infra.controller;

import com.github.io.MateusHCandido.account_service.application.usecases.account.*;
import com.github.io.MateusHCandido.account_service.configuration.AccountMapper;
import com.github.io.MateusHCandido.account_service.domain.Account;
import com.github.io.MateusHCandido.account_service.infra.controller.dto.AccountRequestDto;
import com.github.io.MateusHCandido.account_service.infra.controller.dto.AccountRequestUpdateDto;
import com.github.io.MateusHCandido.account_service.infra.controller.dto.AccountResponseDto;
import com.github.io.MateusHCandido.account_service.infra.gateway.AccountEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private FindByAccountNumberUseCase findByAccountNumberUseCase;
    @Autowired
    private FindByAccountCpfCnpjUseCase findByAccountCpfCnpjUseCase;
    @Autowired
    private FindByAccountEmailUseCase findByAccountEmailUseCase;
    @Autowired
    private SaveAccountUseCase saveAccountUseCase;
    @Autowired
    private UpdateEmailAccountUseCase updateEmailAccountUseCase;

    @Autowired
    private AccountEntityService accountEntityService;

    @Autowired
    private AccountMapper mapper;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDto saveCustomer(@RequestBody @Valid AccountRequestDto dto){
        Account account = saveAccountUseCase.saveAccountUseCase(mapper.convertToDomain(dto));
        return mapper.convertToResponseBody(account);
    }

    @PutMapping("/update/email")
    @ResponseStatus(HttpStatus.OK)
    public AccountResponseDto updateEmail(@RequestBody @Valid AccountRequestUpdateDto dto){
        Account account = updateEmailAccountUseCase.updateEmailAccountUseCase(dto.getAccountNumber(), dto.getNewEmail());
        return mapper.convertToResponseBody(account);
    }

    @GetMapping("/find/by/cpfcnpj")
    public AccountResponseDto findAccountByCpfCnpj(@RequestParam String cpfCnpj){
        Account account = findByAccountCpfCnpjUseCase.findByAccountCpfCnpjUseCase(cpfCnpj);
        return mapper.convertToResponseBody(account);
    }

    @GetMapping("/find/by/email")
    public AccountResponseDto findAccountByEmail(@RequestParam String email){
        Account account = findByAccountEmailUseCase.findByAccountEmailUseCase(email);
        return mapper.convertToResponseBody(account);
    }

    @GetMapping("/find/by/numberAccount")
    public AccountResponseDto findByNumberAccount(@RequestParam Long numberAccount){
        Account account = findByAccountNumberUseCase.findByAccountNumberUseCase(numberAccount);
        return mapper.convertToResponseBody(account);
    }

    @PutMapping("/deposit")
    public String deposit(@RequestParam Long numberAccount, @RequestParam String valueForDeposit){
        return accountEntityService.deposit(numberAccount, new BigDecimal( valueForDeposit ));
    }


}
