package com.teamper.ui.message;

import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;

@SpringComponent
public class UIMessageManager {

    @Autowired
    private MessageSource messageSource;
    private Locale locale;

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getMessage(String source, Object... var) {
        return messageSource.getMessage(source, var, locale);
    }
}
