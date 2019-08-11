package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MsgUtil {

    private MessageSource messageSource;

    @Autowired
    public MsgUtil(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getLocalizedMessage(String code, Locale locale) {
        return messageSource.getMessage(code, null, locale);
    }
}
