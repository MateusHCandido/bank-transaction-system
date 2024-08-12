package com.github.io.MateusHCandido.account_service.domain.enums;

public enum TransactionStatus {

    SUCCESSFUL("S"),
    FAILURE("F");
    private final String description;


    TransactionStatus(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public String toString(){
        return this.description;
    }
}
