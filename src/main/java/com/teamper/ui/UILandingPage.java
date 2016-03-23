package com.teamper.ui;

import com.teamper.ui.event.UILoginEvent;
import com.teamper.ui.event.bus.UIEventBus;
import com.teamper.ui.event.listener.UIEventListener;
import com.teamper.ui.message.UIMessageManager;
import com.teamper.ui.module.login.model.dto.CredentialUIDto;
import com.teamper.ui.module.login.view.LoginView;
import com.teamper.ui.module.main.MainView;
import com.teamper.ui.module.root.view.DefaultView;
import com.teamper.ui.module.root.view.RootView;
import com.teamper.ui.navigation.UINavigator;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

@SpringUI
@Theme("teamper")
@Title("Teamper.com")
public class UILandingPage extends UI implements UIEventListener<UILoginEvent> {

    @Autowired
    private UIEventBus uiEventBus;

    @Autowired
    private UINavigator uiNavigator;


    @Autowired
    private UIMessageManager uiMessageManager;

    @Autowired
    private SpringViewProvider springViewProvider;


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        //setLocalization(vaadinRequest.getLocale());
        setLocalization(new Locale("tr"));
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.UI_WITH_MENU);
        RootView rootView = new RootView();
        setContent(rootView);
        Navigator navigator = new Navigator(this, this);
        navigator.addProvider(springViewProvider);
        uiNavigator.setNavigator(navigator);
        setContent(new DefaultView());
        uiEventBus.register(this);
    }

    private void setLocalization(Locale locale) {
        setLocale(locale);
        uiMessageManager.setLocale(locale);
    }

    @Override
    public void eventReceived(UILoginEvent uiLoginEvent) {
        VaadinSession.getCurrent().setAttribute(CredentialUIDto.class.getName(), uiLoginEvent.getCredentialUIDto());
        updateContent();
    }

    private void updateContent() {
        CredentialUIDto user = (CredentialUIDto) VaadinSession.getCurrent().getAttribute(CredentialUIDto.class.getName());
        if (user != null) {
            // Authenticated user
            setContent(new MainView());
            removeStyleName(LoginView.NAME);
            getNavigator().navigateTo(MainView.NAME);
        } else {
            addStyleName(LoginView.NAME);
            setContent(new LoginView());
        }
    }
}
