package com.robertomessabrasil.carreseller.domain.observer.listener.validation;

import com.robertomessabrasil.carreseller.domain.entity.user.event.UserValidationCode;
import com.robertomessabrasil.carreseller.domain.entity.user.event.UserValidationEvent;
import com.robertomessabrasil.carreseller.domain.observer.listener.Event;
import com.robertomessabrasil.carreseller.domain.observer.listener.EventListener;

public class ValidationListener extends EventListener {
    @Override
    public boolean handleEvent(Event event) {
        if (event instanceof UserValidationEvent) {
            UserValidationEvent userValidationEvent = (UserValidationEvent) event;
            if (userValidationEvent.getCodes().contains(UserValidationCode.INVALID_NAME)) {
                return true;
            }
        }
        return false;
    }
}
