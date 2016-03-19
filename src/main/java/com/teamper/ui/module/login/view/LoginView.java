package com.teamper.ui.module.login.view;

import com.teamper.ui.event.UILoginEvent;
import com.teamper.ui.event.bus.UIEventBus;
import com.teamper.ui.message.UIMessageManager;
import com.teamper.ui.module.login.model.dto.CredentialUIDto;
import com.teamper.ui.module.login.model.property.LoginProperty;
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

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        setSizeFull();
        notifyLoginMessage();
        Component loginForm = buildLoginForm();
        addComponent(loginForm);
        setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
    }

    private void notifyLoginMessage() {
        String title = uiMessageManager.getMessage(LoginProperty.NOTIFICATION_TITLE);
        String description = uiMessageManager.getMessage(LoginProperty.NOTIFICATION_DESCRIPTION);
        uiNotificationManager.notify(title, description, 5000);
    }

    private Component buildLoginForm() {
        final VerticalLayout loginPanel = new VerticalLayout();
        loginPanel.setSizeUndefined();
        loginPanel.setSpacing(true);
        Responsive.makeResponsive(loginPanel);
        loginPanel.addStyleName("login-panel");

        loginPanel.addComponent(buildLabels());
        loginPanel.addComponent(buildFields());
        loginPanel.addComponent(new CheckBox(uiMessageManager.getMessage(LoginProperty.LANG_REMEMBER), true));
        return loginPanel;
    }

    private Component buildLabels() {
        CssLayout labels = new CssLayout();
        labels.addStyleName("labels");

        Label welcome = new Label(uiMessageManager.getMessage(LoginProperty.LANG_WELCOME));
        welcome.setSizeUndefined();
        welcome.addStyleName(ValoTheme.LABEL_H4);
        welcome.addStyleName(ValoTheme.LABEL_COLORED);
        labels.addComponent(welcome);

        Label title = new Label(uiMessageManager.getMessage(LoginProperty.LANG_TITLE));
        title.setSizeUndefined();
        title.addStyleName(ValoTheme.LABEL_H3);
        title.addStyleName(ValoTheme.LABEL_LIGHT);
        labels.addComponent(title);
        return labels;
    }

    private Component buildFields() {
        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);
        fields.addStyleName("fields");

        final TextField username = new TextField(uiMessageManager.getMessage(LoginProperty.LANG_EMAIL));
        username.setIcon(FontAwesome.USER);
        username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        final PasswordField password = new PasswordField(uiMessageManager.getMessage(LoginProperty.LANG_PASSWORD));
        password.setIcon(FontAwesome.LOCK);
        password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        final Button signin = new Button(uiMessageManager.getMessage(LoginProperty.LANG_SIGNIN));
        signin.addStyleName(ValoTheme.BUTTON_PRIMARY);
        signin.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        signin.focus();

        fields.addComponents(username, password, signin);
        fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

        signin.addClickListener((Button.ClickListener) event -> {
            UILoginEvent uiLoginEvent = new UILoginEvent();
            CredentialUIDto credentialUIDto = new CredentialUIDto();
            credentialUIDto.setEmail(username.getValue());
            credentialUIDto.setPassword(password.getValue());
            uiLoginEvent.setCredentialUIDto(credentialUIDto);
            uiEventBus.post(uiLoginEvent);
        });
        return fields;
    }

}
