package com.teamper.ui.module.login.view;

import com.teamper.ui.event.UILoginEvent;
import com.teamper.ui.event.bus.UIEventBus;
import com.teamper.ui.message.UIMessageManager;
import com.teamper.ui.model.property.UIStyleProperty;
import com.teamper.ui.module.login.model.dto.CredentialUIDto;
import com.teamper.ui.module.login.model.property.LoginMessageProperty;
import com.teamper.ui.module.login.model.property.LoginStyleProperty;
import com.teamper.ui.module.register.view.RegisterView;
import com.teamper.ui.navigation.UINavigator;
import com.teamper.ui.notification.UINotificationManager;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = LoginView.NAME)
public class LoginView extends VerticalLayout implements View {

    public static final String NAME = "loginView";

    @Autowired
    private UIEventBus uiEventBus;

    @Autowired
    private UINotificationManager uiNotificationManager;

    @Autowired
    private UIMessageManager uiMessageManager;

    @Autowired
    private UINavigator uiNavigator;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        setSizeFull();
        notifyLoginMessage();
        Component loginForm = buildLoginForm();
        addComponent(loginForm);
        setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
    }

    private void notifyLoginMessage() {
        String title = uiMessageManager.getMessage(LoginMessageProperty.NOTIFICATION_TITLE);
        String description = uiMessageManager.getMessage(LoginMessageProperty.NOTIFICATION_DESCRIPTION);
        uiNotificationManager.notify(title, description, LoginStyleProperty.DELAY_IN_MILLI_SECONDS);
    }

    private Component buildLoginForm() {
        final VerticalLayout loginPanel = new VerticalLayout();
        loginPanel.setSizeUndefined();
        loginPanel.setSpacing(true);
        Responsive.makeResponsive(loginPanel);
        loginPanel.addStyleName(LoginStyleProperty.LOGIN_PANEL);
        loginPanel.addComponent(buildPanelLabels());
        loginPanel.addComponent(buildFormFields());
        loginPanel.addComponent(buildRemainingComponents());
        return loginPanel;
    }

    private Component buildPanelLabels() {
        CssLayout labels = new CssLayout();
        labels.addStyleName(LoginStyleProperty.LABELS);

        Label welcome = new Label(uiMessageManager.getMessage(LoginMessageProperty.LANG_WELCOME));
        welcome.setSizeUndefined();
        welcome.addStyleName(UIStyleProperty.LABEL_H4);
        welcome.addStyleName(UIStyleProperty.LABEL_COLORED);
        labels.addComponent(welcome);

        Label title = new Label(uiMessageManager.getMessage(LoginMessageProperty.LANG_TITLE));
        title.setSizeUndefined();
        title.addStyleName(UIStyleProperty.LABEL_H3);
        title.addStyleName(UIStyleProperty.LABEL_LIGHT);
        labels.addComponent(title);
        return labels;
    }

    private Component buildFormFields() {
        VerticalLayout formFields = new VerticalLayout();
        formFields.setSpacing(true);
        formFields.addStyleName(LoginStyleProperty.FIELDS);

        final TextField email = new TextField(uiMessageManager.getMessage(LoginMessageProperty.LANG_EMAIL));
        email.setIcon(FontAwesome.ENVELOPE);
        email.addStyleName(UIStyleProperty.TEXTFIELD_INLINE_ICON);

        final PasswordField password = new PasswordField(uiMessageManager.getMessage(LoginMessageProperty.LANG_PASSWORD));
        password.setIcon(FontAwesome.LOCK);
        password.addStyleName(UIStyleProperty.TEXTFIELD_INLINE_ICON);

        final Button signin = new Button(uiMessageManager.getMessage(LoginMessageProperty.LANG_SIGNIN));
        signin.addStyleName(UIStyleProperty.BUTTON_PRIMARY);
        signin.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        signin.focus();

        formFields.addComponents(email, password, signin);
        formFields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

        signin.addClickListener((Button.ClickListener) event -> {
            UILoginEvent uiLoginEvent = new UILoginEvent();
            CredentialUIDto credentialUIDto = new CredentialUIDto();
            credentialUIDto.setEmail(email.getValue());
            credentialUIDto.setPassword(password.getValue());
            uiLoginEvent.setCredentialUIDto(credentialUIDto);
            uiEventBus.post(uiLoginEvent);
        });
        return formFields;
    }

    private Component buildRemainingComponents() {
        VerticalLayout remainingFields = new VerticalLayout();
        remainingFields.setSpacing(true);
        remainingFields.addComponent(new CheckBox(uiMessageManager.getMessage(LoginMessageProperty.LANG_REMEMBER), true));
        Button signUpButton = new Button(uiMessageManager.getMessage(LoginMessageProperty.LANG_SIGNUP));
        signUpButton.setSizeUndefined();
        signUpButton.addStyleName(ValoTheme.BUTTON_TINY);
        signUpButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        signUpButton.addClickListener((Button.ClickListener) event -> uiNavigator.navigateTo(RegisterView.NAME));
        remainingFields.addComponent(signUpButton);
        remainingFields.setComponentAlignment(signUpButton, Alignment.BOTTOM_LEFT);
        return remainingFields;
    }

}
