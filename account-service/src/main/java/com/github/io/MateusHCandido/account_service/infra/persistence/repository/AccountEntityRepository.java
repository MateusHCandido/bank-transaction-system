package com.github.io.MateusHCandido.account_service.infra.persistence.repository;

import com.github.io.MateusHCandido.account_service.infra.persistence.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountEntityRepository extends JpaRepository<AccountEntity, Long> {
    Boolean existsByAccountCpfCnpj(String accountCpfCnpj);
    Boolean existsByAccountEmail(String accountEmail);

    Boolean existsByAccountNumber(Long accountNumber);
    Optional<AccountEntity> findByAccountCpfCnpj(String accountCpfCnpj);
    Optional<AccountEntity> findByAccountEmail(String accountCpfCnpj);

    @Modifying
    @Transactional
    @Query("UPDATE AccountEntity a SET a.accountEmail = :accountEmail WHERE a.accountNumber = :accountNumber")
    void updateAccountEmailByAccountNumber(@Param("accountEmail") String accountName
                                         , @Param("accountNumber") Long accountNumber);

    @Modifying
    @Transactional
    @Query("UPDATE AccountEntity a " +
            "SET a.accountBalance = :accountBalance " +
            "WHERE a.accountNumber = :accountNumber")
    void updateAccountBalanceByAccountNumber(@Param("accountBalance") BigDecimal accountBalance,
                                                     @Param("accountNumber") AccountEntity accountNumber);


}
