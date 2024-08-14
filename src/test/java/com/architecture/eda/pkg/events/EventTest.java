package com.architecture.eda.pkg.events;

import java.time.LocalDateTime;

class EventTest implements EventInterface {
    private final String name;
    private final LocalDateTime dateTime;
    private Object payload;

    public EventTest(String name, Object payload) {
        this.name = name;
        this.payload = payload;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public Object getPayload() {
        return payload;
    }

    @Override
    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
