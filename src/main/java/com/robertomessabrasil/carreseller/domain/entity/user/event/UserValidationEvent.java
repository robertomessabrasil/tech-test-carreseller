package com.robertomessabrasil.carreseller.domain.entity.user.event;

import com.robertomessabrasil.carreseller.domain.observer.listener.Event;

import java.util.ArrayList;
import java.util.List;

public class UserValidationEvent extends Event {
    private final List<UserValidationCode> codes = new ArrayList<>();

    public UserValidationEvent addCode(UserValidationCode code) {
        this.codes.add(code);
        return this;
    }

    public List<UserValidationCode> getCodes() {
        return codes;
    }
}
