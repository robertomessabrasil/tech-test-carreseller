package com.robertomessabrasil.carreseller.domain.observer.listener.validation;

import com.robertomessabrasil.carreseller.domain.entity.store.event.StoreValidationCode;
import com.robertomessabrasil.carreseller.domain.entity.store.event.StoreValidationEvent;
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
                userValidationEvent.getUserEntity().setName("New User");
            }
            if (userValidationEvent.getCodes().contains(UserValidationCode.INVALID_EMAIL)) {
                return true;
            }
        }
        if (event instanceof StoreValidationEvent) {
            StoreValidationEvent storeValidationEvent = (StoreValidationEvent) event;
            if (storeValidationEvent.getCodes().contains(StoreValidationCode.INVALID_NAME)) {
                return true;
            }
            if (storeValidationEvent.getCodes().contains(StoreValidationCode.INVALID_CNPJ)) {
                storeValidationEvent.getStoreEntity().setCnpj("000000000000000");
            }
        }
        return false;
    }
}
