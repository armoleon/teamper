package com.teamper.ui.module.register.view;

import com.teamper.ui.event.UIRegisterEvent;
import com.teamper.ui.event.bus.UIEventBus;
import com.teamper.ui.message.UIMessageManager;
import com.teamper.ui.module.register.model.dto.RegisterUIDto;
import com.teamper.ui.module.register.model.property.RegisterMessageProperty;
import com.teamper.ui.module.register.model.property.RegisterStyleProperty;
import com.teamper.ui.notification.UINotificationManager;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = RegisterView.NAME)
public class RegisterView extends VerticalLayout implements View {

    public static final String NAME = "registerView";

    @Autowired
    private UIEventBus uiEventBus;

    @Autowired
    private UINotificationManager uiNotificationManager;

    @Autowired
    private UIMessageManager uiMessageManager;


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        setSizeFull();
        Component registerForm = buildRegisterForm();
        addComponent(registerForm);
        setComponentAlignment(registerForm, Alignment.MIDDLE_CENTER);
    }

    private Component buildRegisterForm() {
        final VerticalLayout registerPanel = new VerticalLayout();
        registerPanel.setSizeUndefined();
        registerPanel.setSpacing(true);
        Responsive.makeResponsive(registerPanel);
        registerPanel.addStyleName(RegisterStyleProperty.LOGIN_PANEL);
        registerPanel.addComponent(buildLabels());
        registerPanel.addComponent(buildFields());
        registerPanel.addComponent(new CheckBox(uiMessageManager.getMessage(RegisterMessageProperty.LANG_REMEMBER), true));
        return registerPanel;
    }

    private Component buildLabels() {
        CssLayout labels = new CssLayout();
        labels.addStyleName(RegisterStyleProperty.LABELS);

        Label welcome = new Label(uiMessageManager.getMessage(RegisterMessageProperty.LANG_WELCOME));
        welcome.setSizeUndefined();
        welcome.addStyleName(RegisterStyleProperty.LABEL_H4);
        welcome.addStyleName(RegisterStyleProperty.LABEL_COLORED);
        labels.addComponent(welcome);

        Label title = new Label(uiMessageManager.getMessage(RegisterMessageProperty.LANG_TITLE));
        title.setSizeUndefined();
        title.addStyleName(RegisterStyleProperty.LABEL_H3);
        title.addStyleName(RegisterStyleProperty.LABEL_LIGHT);
        labels.addComponent(title);
        return labels;
    }

    private Component buildFields() {
        VerticalLayout fields = new VerticalLayout();
        fields.setSpacing(true);
        fields.addStyleName(RegisterStyleProperty.FIELDS);

        final TextField email = new TextField(uiMessageManager.getMessage(RegisterMessageProperty.LANG_EMAIL));
        email.setIcon(FontAwesome.ENVELOPE);
        email.addStyleName(RegisterStyleProperty.TEXTFIELD_INLINE_ICON);

        final PasswordField password = new PasswordField(uiMessageManager.getMessage(RegisterMessageProperty.LANG_PASSWORD));
        password.setIcon(FontAwesome.LOCK);
        password.addStyleName(RegisterStyleProperty.TEXTFIELD_INLINE_ICON);

        final Button signin = new Button(uiMessageManager.getMessage(RegisterMessageProperty.LANG_SIGNIN));
        signin.addStyleName(RegisterStyleProperty.BUTTON_PRIMARY);
        signin.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        signin.focus();

        fields.addComponents(email, password, signin);
        fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

        signin.addClickListener((Button.ClickListener) event -> {
            UIRegisterEvent uiRegisterEvent = new UIRegisterEvent();
            RegisterUIDto registerUIDto = new RegisterUIDto();

            uiEventBus.post(uiRegisterEvent);
        });
        return fields;
    }
}
