package com.robertomessabrasil.carreseller.domain.entity.user;

public enum UserRoleEnum {
    ADMIN(0), STORE_OWNER(1), STORE_MANAGER(2), STORE_USER(3);

    private final int value;

    UserRoleEnum(int role) {
        this.value = role;
    }

    public int getValue() {
        return value;
    }
}
