package com.teamper.ui.event.bus;

public interface UIEventBus {
    void post(Object event);

    void register(Object object);

    void unregister(Object object);
}
