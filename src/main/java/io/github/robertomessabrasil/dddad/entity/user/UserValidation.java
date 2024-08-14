package io.github.robertomessabrasil.dddad.entity.user;

import io.github.robertomessabrasil.dddad.entity.user.event.UserValidationCode;
import io.github.robertomessabrasil.dddad.entity.user.event.UserValidationEvent;
import io.github.robertomessabrasil.jwatch.exception.InterruptException;
import io.github.robertomessabrasil.jwatch.observer.EventObserver;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

public class UserValidation {

    public static void validateStoreUser(UserEntity userEntity, EventObserver eventObserver) throws InterruptException {

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);
        for (ConstraintViolation<UserEntity> violation : violations) {

            if (violation.getMessage().equals(UserEntity.NAME)) {
                UserValidationEvent userValidationEvent = new UserValidationEvent();
                userValidationEvent.setUser(userEntity);
                userValidationEvent.setCode(UserValidationCode.INVALID_NAME);
                eventObserver.notify(userValidationEvent);
            }

            if (violation.getMessage().equals(UserEntity.EMAIL)) {
                UserValidationEvent userValidationEvent = new UserValidationEvent();
                userValidationEvent.setUser(userEntity);
                userValidationEvent.setCode(UserValidationCode.INVALID_EMAIL);
                eventObserver.notify(userValidationEvent);
            }

        }

        if ((userEntity.getRole() == null) || !(userEntity.getRole().getRoleEnum().equals(UserRoleEnum.STORE_USER))) {
            UserValidationEvent userValidationEvent = new UserValidationEvent();
            userValidationEvent.setUser(userEntity);
            userValidationEvent.setCode(UserValidationCode.INVALID_ROLE);
            eventObserver.notify(userValidationEvent);
        }

    }
}
