package com.teamper.ui.notification;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Notification;

@SpringComponent
public class UINotificationManager {

    public void notify(String title, String description, Integer delayInMilliSeconds) {
        Notification notification = new Notification(title);
        notification.setDescription(description);
        notification.setHtmlContentAllowed(true);
        notification.setStyleName("tray dark small closable login-help");
        notification.setPosition(Position.BOTTOM_CENTER);
        notification.setDelayMsec(delayInMilliSeconds);
        notification.show(Page.getCurrent());

    }
}
