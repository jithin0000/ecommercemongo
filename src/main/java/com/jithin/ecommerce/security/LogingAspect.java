package com.jithin.ecommerce.security;

import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogingAspect {

    private LoginEventPublisher publisher;

    @Autowired
    public void setPublisher(LoginEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Pointcut("execution(* org.springframework.security.authentication.AuthenticationProvider.authenticate(..))")
    public void doAuthenticate() {
    }

    @Before("com.jithin.ecommerce.security.LogingAspect.doAuthenticate() && args(authentication)")
    public void logBefore(Authentication authentication) {
        System.out.println("before  authentication method : " + authentication.isAuthenticated());
    }

    @AfterReturning(value = "com.jithin.ecommerce.security.LogingAspect.doAuthenticate()", returning = "authentication")
    public void loginAfterAuthenticate(Authentication authentication) {
        System.out.println("After authentication " + authentication.isAuthenticated());
    }

    @AfterThrowing("com.jithin.ecommerce.security.LogingAspect.doAuthenticate() && args(authentication)")
    public void logAuthException(Authentication authentication) {
        String userDetails = (String) authentication.getPrincipal();
        System.out.println("login failed "+userDetails);

        this.publisher.publish(new LoginFailureEvent(authentication));
    }
}
