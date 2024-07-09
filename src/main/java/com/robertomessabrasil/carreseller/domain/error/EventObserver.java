package com.robertomessabrasil.carreseller.domain.error;

import com.robertomessabrasil.carreseller.domain.exception.ObserverException;

import java.util.ArrayList;
import java.util.List;

public class EventObserver {
    private boolean mustInterrupt = false;
    private List<EventListener> listeners = new ArrayList<>();

    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(EventListener listener) {
        listeners.remove(listener);
    }

    public void notify(Event event) throws ObserverException {

        boolean mustInterrupt = false;

        for (EventListener eventListener : this.listeners) {
            for (Event eventOfInterest : eventListener.getEvents()) {
                if (event.getCodeEnum().equals(eventOfInterest.getCodeEnum())) {
                    if (eventListener.handleEvent(event)) {
                        mustInterrupt = true;
                    }
                }
            }
        }

        if (mustInterrupt) {
            throw new ObserverException();
        }

    }

    public boolean isMustInterrupt() {
        return mustInterrupt;
    }

    public EventObserver setMustInterrupt(boolean mustInterrupt) {
        this.mustInterrupt = mustInterrupt;
        return this;
    }

    public List<EventListener> getListeners() {
        return listeners;
    }

    public EventObserver setListeners(List<EventListener> listeners) {
        this.listeners = listeners;
        return this;
    }
}
