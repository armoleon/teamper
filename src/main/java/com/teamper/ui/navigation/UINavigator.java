package com.teamper.ui.navigation;

import com.vaadin.navigator.Navigator;

public interface UINavigator {
    void setNavigator(Navigator navigator);

    void navigateTo(String viewName);
}
