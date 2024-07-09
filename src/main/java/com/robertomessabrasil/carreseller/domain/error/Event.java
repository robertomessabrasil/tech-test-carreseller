package com.robertomessabrasil.carreseller.domain.error;

public class Event {
    private EventCodeEnum codeEnum;
    private String[] messages;

    public Event(EventCodeEnum codeEnum) {
        this.codeEnum = codeEnum;
    }

    public EventCodeEnum getCodeEnum() {
        return codeEnum;
    }

    public Event setCodeEnum(EventCodeEnum codeEnum) {
        this.codeEnum = codeEnum;
        return this;
    }

    public String[] getMessages() {
        return messages;
    }

    public Event setMessages(String[] messages) {
        this.messages = messages;
        return this;
    }
}
