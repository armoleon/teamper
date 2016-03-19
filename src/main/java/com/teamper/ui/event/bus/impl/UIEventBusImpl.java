package com.teamper.ui.event.bus.impl;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import com.teamper.ui.event.bus.UIEventBus;
import com.vaadin.spring.annotation.VaadinSessionScope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@VaadinSessionScope
public class UIEventBusImpl implements UIEventBus, SubscriberExceptionHandler {

    private EventBus eventBus;

    @PostConstruct
    private void init() {
        eventBus = new EventBus(this);
    }

    public void post(Object event) {
        eventBus.post(event);
    }

    public void register(Object object) {
        eventBus.register(object);
    }

    public void unregister(Object object) {
        eventBus.unregister(object);
    }

    @Override
    public void handleException(Throwable throwable, SubscriberExceptionContext subscriberExceptionContext) {

    }
}
