package com.teamper.ui.module.root.view;

import com.teamper.ui.module.login.view.LoginView;
import com.teamper.ui.navigation.UINavigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = DefaultView.NAME)
public class DefaultView extends VerticalLayout implements View {

    public static final String NAME = "";

    @Autowired
    private UINavigator uiNavigator;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        uiNavigator.navigateTo(LoginView.NAME);
    }
}
