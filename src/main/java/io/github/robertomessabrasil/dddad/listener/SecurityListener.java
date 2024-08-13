package io.github.robertomessabrasil.dddad.listener;

import io.github.robertomessabrasil.dddad.service.user.event.InvalidRoleEvent;
import io.github.robertomessabrasil.jwatch.observer.listener.Event;
import io.github.robertomessabrasil.jwatch.observer.listener.EventListener;

public class SecurityListener extends EventListener {
    @Override
    public boolean handleEvent(Event event) {
        if (event instanceof InvalidRoleEvent) {
            return true;
        }
        return false;
    }
}
