package com.jithin.ecommerce.security;

import org.springframework.context.ApplicationEvent;

public class LoginFailureEvent extends ApplicationEvent {
    public LoginFailureEvent(Object source) {
        super(source);
    }
}
