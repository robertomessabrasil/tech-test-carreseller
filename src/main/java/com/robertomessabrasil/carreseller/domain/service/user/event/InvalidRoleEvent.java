package com.robertomessabrasil.carreseller.domain.service.user.event;


import com.robertomessabrasil.carreseller.domain.entity.user.UserRoleEnum;
import io.github.robertomessabrasil.jwatch.observer.listener.Event;

public class InvalidRoleEvent extends Event {
    final private UserRoleEnum roleEnum;

    public InvalidRoleEvent(UserRoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    public UserRoleEnum getRoleEnum() {
        return roleEnum;
    }

    public String toString() {
        return "Role not authorized: " + this.roleEnum;
    }
}
