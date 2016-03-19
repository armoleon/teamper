package com.teamper.ui.message;

import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

@SpringComponent
public class UIMessageManager {

    @Autowired
    private MessageSource messageSource;


}
