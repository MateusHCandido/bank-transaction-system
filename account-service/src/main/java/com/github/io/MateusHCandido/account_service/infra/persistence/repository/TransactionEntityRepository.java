package com.github.io.MateusHCandido.account_service.infra.persistence.repository;


import com.github.io.MateusHCandido.account_service.infra.persistence.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionEntityRepository extends JpaRepository<TransactionEntity, Long> {
}
