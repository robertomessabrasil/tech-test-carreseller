package com.robertomessabrasil.carreseller.domain.observer.listener;

import java.util.ArrayList;
import java.util.List;

abstract public class EventListener {
    private List<Class<? extends Event>> eventsOfInterest = new ArrayList<>();

    public boolean handleEvent(Event event) {
        return event.isInterrupt();
    }

    public void setEventsOfInterest(List<Class<? extends Event>> eventsOfInterest) {
        this.eventsOfInterest = eventsOfInterest;
    }

    public List<Class<? extends Event>> getEventsOfInterest() {
        return this.eventsOfInterest;
    }

}
