package com.github.io.MateusHCandido.account_service.domain.enums;

public enum AccountType {
    NATURAL_PERSON("N"),
    LEGAL_PERSON("L");

    private final String description;


    AccountType(String description) {
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
