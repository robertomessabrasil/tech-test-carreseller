package io.github.robertomessabrasil.dddad.service.user.event;


import io.github.robertomessabrasil.dddad.entity.user.UserRoleEnum;
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
