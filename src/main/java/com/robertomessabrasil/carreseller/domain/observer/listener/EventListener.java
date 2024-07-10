package com.robertomessabrasil.carreseller.domain.observer.listener;

import java.util.ArrayList;
import java.util.List;

abstract public class EventListener {
    private List<Event> eventsOfInterest = new ArrayList<>();

    public boolean handleEvent(Event event) {
        return event.isInterrupt();
    }

    public void setEvents(List<Event> eventsOfInterest) {
        this.eventsOfInterest = eventsOfInterest;
    }

    public List<Event> getEvents() {
        return this.eventsOfInterest;
    }

}
