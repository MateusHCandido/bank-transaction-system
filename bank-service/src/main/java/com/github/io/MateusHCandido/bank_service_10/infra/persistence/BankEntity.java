package com.github.io.MateusHCandido.bank_service_10.infra.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class BankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long bankId;

    @Column(unique = true)
    private Integer bankCode;

    private String bankName;

    public BankEntity(Integer bankCode, String bankName){
        this.bankCode = bankCode;
        this.bankName = bankName;
    }
}
