package com.github.io.MateusHCandido.transaction_log_service.repository;

import com.github.io.MateusHCandido.transaction_log_service.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetails, Long> {
}
