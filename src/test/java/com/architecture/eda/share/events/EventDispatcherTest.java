package com.architecture.eda.share.events;

import com.architecture.eda.share.events.exceptions.HandleAlreadyRegistered;
import com.architecture.eda.share.events.impl.EventDispatcher;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class EventDispatcherTest {

    private EventDispatcher eventDispatcher;
    private EventTest event;
    private EventHandlerInterface handler;
    private EventHandlerInterface handler2;

    @BeforeEach
    void setUp() {
        eventDispatcher = new EventDispatcher();
        event = new EventTest("test", "payload");
        handler = mock(EventHandlerTest.class);
        handler2 = mock(EventHandlerTest.class);
    }

    @Test
    void testRegisterAndDispatch() {
        eventDispatcher.register(event.getName(), handler);
        eventDispatcher.register(event.getName(), handler2);

        eventDispatcher.dispatch(event);

        verify(handler, times(1)).handle(event);
        verify(handler2, times(1)).handle(event);
    }



    @Test
    void testRegisterSameHandlerTwice() {
        eventDispatcher.register(event.getName(), handler);
        
        assertEquals(1, eventDispatcher.getHandlers().get(event.getName()).size());
    }

    @Test
    void testHandleAlreadyRegistered() {
        eventDispatcher.register(event.getName(), handler);
        assertEquals(1, eventDispatcher.getHandlers().get(event.getName()).size());

        HandleAlreadyRegistered exception = assertThrows(
                HandleAlreadyRegistered.class,
                () -> eventDispatcher.register(event.getName(), handler),
                "Expected register() to throw, but it didn't"
        );

        assertEquals("Handler already registered for event: " + event.getName(), exception.getMessage());
    }



    @Test
    void testHas() {
        eventDispatcher.register(event.getName(), handler);

        assertTrue(eventDispatcher.has(event.getName(), handler));
        assertFalse(eventDispatcher.has(event.getName(), handler2));
    }

    @Test
    void testRemove() {
        eventDispatcher.register(event.getName(), handler);
        eventDispatcher.remove(event.getName(), handler);

        assertFalse(eventDispatcher.has(event.getName(), handler));
    }

    @Test
    void testClear() {
        eventDispatcher.register(event.getName(), handler);
        assertTrue(eventDispatcher.has(event.getName(), handler));

        eventDispatcher.clear();

        assertFalse(eventDispatcher.has(event.getName(), handler));
    }
}
