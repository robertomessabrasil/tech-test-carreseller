package io.github.robertomessabrasil.dddad.domain.repository.event;

import io.github.robertomessabrasil.dddad.domain.entity.user.UserEntity;
import io.github.robertomessabrasil.dddad.domain.entity.user.event.UserValidationCode;
import io.github.robertomessabrasil.jwatch.observer.listener.Event;

public class UserRepositoryEvent extends Event {
    private Exception exception;

    public UserRepositoryEvent(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return this.exception;
    }

    @Override
    public String toString() {
        return this.exception.getMessage();
    }
}
