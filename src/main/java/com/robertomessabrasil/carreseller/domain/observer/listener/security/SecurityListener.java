package com.robertomessabrasil.carreseller.domain.observer.listener.security;

import com.robertomessabrasil.carreseller.domain.observer.listener.Event;
import com.robertomessabrasil.carreseller.domain.observer.listener.EventListener;
import com.robertomessabrasil.carreseller.domain.service.user.event.InvalidRoleEvent;

public class SecurityListener extends EventListener {
    @Override
    public boolean handleEvent(Event event) {
        if (event instanceof InvalidRoleEvent) {
            return true;
        }
        return false;
    }
}
