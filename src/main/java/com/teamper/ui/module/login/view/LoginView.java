package com.teamper.ui.module.login.view;

import com.teamper.ui.event.UILoginEvent;
import com.teamper.ui.event.bus.UIEventBus;
import com.teamper.ui.message.UIMessageManager;
import com.teamper.ui.module.login.model.dto.CredentialUIDto;
import com.teamper.ui.module.login.model.property.LoginMessageProperty;
import com.teamper.ui.module.login.model.property.LoginStyleProperty;
import com.teamper.ui.notification.UINotificationManager;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
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
        loginPanel.addComponent(buildLabels());
        loginPanel.addComponent(buildFields());
        loginPanel.addComponent(new CheckBox(uiMessageManager.getMessage(LoginMessageProperty.LANG_REMEMBER), true));
        return loginPanel;
    }

    private Component buildLabels() {
        CssLayout labels = new CssLayout();
        labels.addStyleName(LoginStyleProperty.LABELS);

        Label welcome = new Label(uiMessageManager.getMessage(LoginMessageProperty.LANG_WELCOME));
        welcome.setSizeUndefined();
        welcome.addStyleName(LoginStyleProperty.LABEL_H4);
        welcome.addStyleName(LoginStyleProperty.LABEL_COLORED);
        labels.addComponent(welcome);

        Label title = new Label(uiMessageManager.getMessage(LoginMessageProperty.LANG_TITLE));
        title.setSizeUndefined();
        title.addStyleName(LoginStyleProperty.LABEL_H3);
        title.addStyleName(LoginStyleProperty.LABEL_LIGHT);
        labels.addComponent(title);
        return labels;
    }

    private Component buildFields() {
        VerticalLayout fields = new VerticalLayout();
        fields.setSpacing(true);
        fields.addStyleName(LoginStyleProperty.FIELDS);

        final TextField email = new TextField(uiMessageManager.getMessage(LoginMessageProperty.LANG_EMAIL));
        email.setIcon(FontAwesome.ENVELOPE);
        email.addStyleName(LoginStyleProperty.TEXTFIELD_INLINE_ICON);

        final PasswordField password = new PasswordField(uiMessageManager.getMessage(LoginMessageProperty.LANG_PASSWORD));
        password.setIcon(FontAwesome.LOCK);
        password.addStyleName(LoginStyleProperty.TEXTFIELD_INLINE_ICON);

        final Button signin = new Button(uiMessageManager.getMessage(LoginMessageProperty.LANG_SIGNIN));
        signin.addStyleName(LoginStyleProperty.BUTTON_PRIMARY);
        signin.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        signin.focus();

        fields.addComponents(email, password, signin);
        fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

        signin.addClickListener((Button.ClickListener) event -> {
            UILoginEvent uiLoginEvent = new UILoginEvent();
            CredentialUIDto credentialUIDto = new CredentialUIDto();
            credentialUIDto.setEmail(email.getValue());
            credentialUIDto.setPassword(password.getValue());
            uiLoginEvent.setCredentialUIDto(credentialUIDto);
            uiEventBus.post(uiLoginEvent);
        });
        return fields;
    }

}
