package com.github.io.MateusHCandido.transaction_log_service.service;

import com.github.io.MateusHCandido.transaction_log_service.entity.TransactionDetails;
import com.github.io.MateusHCandido.transaction_log_service.repository.TransactionDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionDetailService {


    @Autowired
    private TransactionDetailRepository repository;

    public void saveTransactionDetail(TransactionDetails transactionDetails){
        repository.save(transactionDetails);
    }
}
