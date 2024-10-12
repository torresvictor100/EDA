package com.architecture.eda.share.events.impl;

import com.architecture.eda.share.events.EventInterface;

import java.time.LocalDateTime;

public class EventTransactionCreated implements EventInterface {
    private final String name;
    private Object payload;
    private final LocalDateTime dateTime;

    public EventTransactionCreated() {
        this.name = "EventTransactionCreated";
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getPayload() {
        return payload;
    }

    @Override
    public void setPayload(Object payload) {
        this.payload = payload;
    }

    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}