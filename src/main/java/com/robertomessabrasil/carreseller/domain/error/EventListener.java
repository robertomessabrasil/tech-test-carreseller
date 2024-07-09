package com.robertomessabrasil.carreseller.domain.error;

import com.robertomessabrasil.carreseller.domain.exception.ObserverException;

import java.util.List;

public interface EventListener {
    boolean handleEvent(Event event) throws ObserverException;

    void reset();

    boolean hasErrors();

    String[] getMessages();

    void setEvents(List<Event> codes);

    List<Event> getEvents();
}
