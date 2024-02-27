package com.konoha;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;

public class CustomEventListenerProvider implements EventListenerProvider {
    @Override
    public void onEvent(Event event) {
        // Log event
        System.out.println("Event Occurred: " + event.getType());
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        // Not used in this example
    }

    @Override
    public void close() {

    }
}

