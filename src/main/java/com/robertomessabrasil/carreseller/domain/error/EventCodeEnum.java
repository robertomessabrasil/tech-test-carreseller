package com.robertomessabrasil.carreseller.domain.error;

public enum EventCodeEnum {
    ERROR_FORBIDDEN(000), ERROR_INVALIDINPUT(001), REPO_USERNOTFOUND(100);

    private final int code;

    EventCodeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
