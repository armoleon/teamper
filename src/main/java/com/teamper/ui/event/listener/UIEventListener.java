package com.teamper.ui.event.listener;

import com.google.common.eventbus.Subscribe;
import com.teamper.ui.event.UIEvent;

public interface UIEventListener<T extends UIEvent> {

    @Subscribe
    void eventReceived(T t);
}
