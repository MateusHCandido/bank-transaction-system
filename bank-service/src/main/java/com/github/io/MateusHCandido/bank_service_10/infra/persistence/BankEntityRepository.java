package com.github.io.MateusHCandido.bank_service_10.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankEntityRepository extends JpaRepository<BankEntity, Long> {

    Optional<BankEntity> findByBankCode(Integer code);

    List<BankEntity> findByBankName(String name);

}
