package com.robertomessabrasil.carreseller.valueobject;

public final class UserRoleVO {

    private final UserRoleEnum roleEnum;

    public static UserRoleVO buildAdminRole() {
        return new UserRoleVO(UserRoleEnum.ADMIN);
    }

    public static UserRoleVO buildUserRole() {
        return new UserRoleVO(UserRoleEnum.STORE_USER);
    }

    private UserRoleVO(UserRoleEnum role) {
        this.roleEnum = role;
    }

    public UserRoleEnum getRoleEnum() {
        return roleEnum;
    }
}