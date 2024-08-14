package com.architecture.eda.pkg.events;

public interface EventDispatcherInterface {
    void register(String eventName, EventHandlerInterface handle);
    void dispatch(EventInterface event) throws Exception;
    void remove(String eventName, EventHandlerInterface handler) throws Exception;
    boolean has(String eventName, EventHandlerInterface handler);
    void clear();
}
