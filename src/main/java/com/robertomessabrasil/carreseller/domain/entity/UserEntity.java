package com.robertomessabrasil.carreseller.domain.entity;

import com.robertomessabrasil.carreseller.domain.error.Event;
import com.robertomessabrasil.carreseller.domain.error.EventCodeEnum;
import com.robertomessabrasil.carreseller.domain.error.EventObserver;
import com.robertomessabrasil.carreseller.domain.exception.ObserverException;
import com.robertomessabrasil.carreseller.valueobject.UserRoleVO;

import java.util.ArrayList;
import java.util.List;

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

    public void validate(EventObserver observer) throws ObserverException {
        List<String> messages = new ArrayList<>();
        if (this.getName() == null) {
            messages.add("invalid name");
        }
        if (this.getEmail() == null) {
            messages.add("invalid email");
        }
        Event event = new Event(EventCodeEnum.ERROR_INVALIDINPUT).setMessages(messages.toArray(new String[0]));
        observer.notify(event);
    }
}

