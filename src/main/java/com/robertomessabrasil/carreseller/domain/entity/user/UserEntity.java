package com.robertomessabrasil.carreseller.domain.entity.user;

import com.robertomessabrasil.carreseller.domain.entity.user.event.UserValidationCode;
import com.robertomessabrasil.carreseller.domain.entity.user.event.UserValidationEvent;
import com.robertomessabrasil.carreseller.domain.exception.InterruptException;
import com.robertomessabrasil.carreseller.domain.observer.EventObserver;
import com.robertomessabrasil.carreseller.valueobject.UserRoleVO;

public class UserEntity {

    private int id;
    private String name;
    private String email;
    private UserRoleVO role;

    public int getId() {
        return id;
    }

    public UserEntity setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserRoleVO getRole() {
        return role;
    }

    public UserEntity setRole(UserRoleVO role) {
        this.role = role;
        return this;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void validate(EventObserver observer) throws InterruptException {
        UserValidationEvent userValidationEvent = new UserValidationEvent();
        if (this.getName() == null) {
            userValidationEvent.addCode(UserValidationCode.INVALID_NAME);
        }
        if (this.getEmail() == null) {
            userValidationEvent.addCode(UserValidationCode.INVALID_EMAIL);
        }
        observer.notify(userValidationEvent);
    }
}

