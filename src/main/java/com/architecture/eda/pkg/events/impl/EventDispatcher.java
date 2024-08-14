package com.architecture.eda.pkg.events.impl;

import com.architecture.eda.pkg.events.EventDispatcherInterface;
import com.architecture.eda.pkg.events.EventHandlerInterface;

import com.architecture.eda.pkg.events.EventInterface;
import com.architecture.eda.pkg.events.exceptions.HandleAlreadyRegistered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventDispatcher implements EventDispatcherInterface{
        private final Map<String, List<EventHandlerInterface>> handlers = new ConcurrentHashMap<>();

    @Autowired
    public void dispatch(EventInterface event) {
        List<EventHandlerInterface> eventHandlers = handlers.get(event.getName());
        if (eventHandlers != null) {
            //way to create multithreads
            ExecutorService executor = Executors.newCachedThreadPool();
            CountDownLatch latch = new CountDownLatch(eventHandlers.size());

            for (EventHandlerInterface handler : eventHandlers) {
                executor.submit(() -> {
                    try {
                        handler.handle(event);
                    } finally {
                        latch.countDown();
                    }
                });
            }
            endThreads(latch, executor);
        }
    }

    private static void endThreads(CountDownLatch latch, ExecutorService executor) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            executor.shutdown();
        }
    }

    @Autowired
    public synchronized void register(String eventName, EventHandlerInterface handler) {
        List<EventHandlerInterface> eventHandlers = handlers.computeIfAbsent(eventName, k -> new ArrayList<>());

        if (eventHandlers.contains(handler)) {
            throw new HandleAlreadyRegistered("Handler already registered for event: " + eventName);
        }

        eventHandlers.add(handler);
    }

    @Autowired
    public synchronized boolean has(String eventName, EventHandlerInterface handler) {
        List<EventHandlerInterface> eventHandlers = handlers.get(eventName);
        return eventHandlers != null && eventHandlers.contains(handler);
    }

    @Autowired
    public synchronized void remove(String eventName, EventHandlerInterface handler) {
        List<EventHandlerInterface> eventHandlers = handlers.get(eventName);
        if (eventHandlers != null) {
            eventHandlers.remove(handler);
        }
    }

    @Autowired
    public synchronized void clear() {
        handlers.clear();
    }

    public Map<String, List<EventHandlerInterface>> getHandlers() {
        return handlers;
    }
}
