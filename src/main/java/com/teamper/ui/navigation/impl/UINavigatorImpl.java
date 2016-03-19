package com.teamper.ui.navigation.impl;

import com.teamper.ui.navigation.UINavigator;
import com.vaadin.navigator.Navigator;
import com.vaadin.spring.annotation.VaadinSessionScope;
import org.springframework.stereotype.Service;

@Service
@VaadinSessionScope
public class UINavigatorImpl implements UINavigator {

    private Navigator navigator;

    @Override
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void navigateTo(String viewName) {
        navigator.navigateTo(viewName);
    }
}
