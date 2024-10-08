package com.architecture.eda.share.events;

import java.time.LocalDateTime;

public interface EventInterface {
    String getName();
    LocalDateTime getDateTime();
    Object getPayload();
    void setPayload(Object payload);
}
