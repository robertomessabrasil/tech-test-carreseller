package io.github.robertomessabrasil.dddad.listener;

import io.github.robertomessabrasil.dddad.domain.entity.user.event.UserValidationCode;
import io.github.robertomessabrasil.dddad.domain.entity.user.event.UserValidationEvent;
import io.github.robertomessabrasil.dddad.domain.repository.event.UserRepositoryEvent;
import io.github.robertomessabrasil.jwatch.observer.listener.Event;
import io.github.robertomessabrasil.jwatch.observer.listener.EventListener;

public class ValidationListener extends EventListener {
    @Override
    public boolean handleEvent(Event event) {
        if (event instanceof UserValidationEvent userValidationEvent) {
            if (userValidationEvent.getCode().equals(UserValidationCode.INVALID_NAME)) {
                userValidationEvent.getUser().setName("New User");
            }
            if (userValidationEvent.getCode().equals(UserValidationCode.INVALID_EMAIL)) {
                return true;
            }
        }
        if(event instanceof UserRepositoryEvent userRepositoryEvent) {
            return true;
        }
        return false;
    }
}
