package com.robertomessabrasil.carreseller.domain.observer.listener;

public abstract class Event {
    private boolean interrupt;

    public boolean isInterrupt() {
        return interrupt;
    }

    public Event setInterrupt(boolean interrupt) {
        this.interrupt = interrupt;
        return this;
    }
}
