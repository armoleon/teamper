package com.teamper.ui.module.root.view;

import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class RootView extends VerticalLayout {
    private final Panel viewContainer;

    public RootView() {
        setSizeFull();
        setMargin(true);
        setSpacing(true);
        viewContainer = new Panel();
        viewContainer.setSizeFull();
        addComponent(viewContainer);
        setExpandRatio(viewContainer, 1.0f);
    }

    public Panel getViewContainer() {
        return viewContainer;
    }
}
