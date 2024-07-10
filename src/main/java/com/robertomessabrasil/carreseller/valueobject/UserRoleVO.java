package com.robertomessabrasil.carreseller.valueobject;

public final class UserRoleVO {

    private final UserRoleEnum roleEnum;

    public UserRoleVO(UserRoleEnum role) {
        this.roleEnum = role;
    }

    public UserRoleEnum getRoleEnum() {
        return roleEnum;
    }
}