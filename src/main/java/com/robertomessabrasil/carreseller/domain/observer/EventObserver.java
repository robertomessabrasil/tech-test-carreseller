package com.robertomessabrasil.carreseller.domain.observer;

import com.robertomessabrasil.carreseller.domain.exception.InterruptException;
import com.robertomessabrasil.carreseller.domain.observer.listener.Event;
import com.robertomessabrasil.carreseller.domain.observer.listener.EventListener;

import java.util.ArrayList;
import java.util.List;

public class EventObserver {
    private boolean interrupt = false;
    private Event interruptEvent;
    private List<EventListener> listeners = new ArrayList<>();

    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    public void notify(Event event) throws InterruptException {

        this.interruptEvent = null;

        for (EventListener eventListener : this.listeners) {
            for (Event eventOfInterest : eventListener.getEvents()) {
                if (event.getClass().equals(eventOfInterest.getClass())) {
                    if (eventListener.handleEvent(event)) {
                        this.interruptEvent = event;
                        throw new InterruptException();
                    }
                }
            }
        }

    }

    public boolean isInterrupt() {
        return interrupt;
    }

    public EventObserver setInterrupt(boolean interrupt) {
        this.interrupt = interrupt;
        return this;
    }

    public List<EventListener> getListeners() {
        return listeners;
    }

    public EventObserver setListeners(List<EventListener> listeners) {
        this.listeners = listeners;
        return this;
    }

    public Event getInterruptEvent() {
        return interruptEvent;
    }

    public EventObserver setInterruptEvent(Event interruptEvent) {
        this.interruptEvent = interruptEvent;
        return this;
    }
}
