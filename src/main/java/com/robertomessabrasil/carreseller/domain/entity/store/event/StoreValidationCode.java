package com.robertomessabrasil.carreseller.domain.entity.store.event;

public enum StoreValidationCode {
    INVALID_NAME(0), INVALID_CNPJ(1);

    private final int code;

    StoreValidationCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
