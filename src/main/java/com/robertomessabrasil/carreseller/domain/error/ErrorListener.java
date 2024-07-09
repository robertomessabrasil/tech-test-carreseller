package com.robertomessabrasil.carreseller.domain.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorListener implements EventListener {
    private List<Event> codes = new ArrayList<>();
    private boolean hasErrors = false;
    private String[] messages;

    @Override
    public boolean handleEvent(Event event) {
        switch (event.getCodeEnum()) {
            case ERROR_FORBIDDEN:
                return true;
            case ERROR_INVALIDINPUT:
                this.messages = event.getMessages();
                this.hasErrors = true;
                for (String message : this.messages) {
                    this.codes.add(event);
                    if (message.contains("invalid name")) {
                        return true;
                    }
                }
                return false;
        }
        return false;
    }

    @Override
    public String[] getMessages() {
        return this.messages;
    }

    @Override
    public void reset() {
        this.codes = new ArrayList<>();
        this.hasErrors = false;
    }

    @Override
    public boolean hasErrors() {
        return this.hasErrors;
    }

    @Override
    public void setEvents(List<Event> codes) {
        this.codes = codes;
    }

    @Override
    public List<Event> getEvents() {
        return this.codes;
    }

}
