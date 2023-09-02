package com.example.demo.security;

import com.example.demo.security.filter.AjaxLoginProcessingFilter;
import com.example.demo.security.handler.AjaxFailureHandler;
import com.example.demo.security.handler.AjaxSuccessHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.*;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import java.util.List;

public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {
    private final SessionRegistry sessionRegistry;
    private CustomDsl(SessionRegistry sessionRegistry){
        this.sessionRegistry = sessionRegistry;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        HttpSessionSecurityContextRepository repository = new HttpSessionSecurityContextRepository();
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(authenticationManager);

        RegisterSessionAuthenticationStrategy registerSessionAuthenticationStrategy =
                new RegisterSessionAuthenticationStrategy(sessionRegistry);
        ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy =
                new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry);
        concurrentSessionControlAuthenticationStrategy.setMaximumSessions(1);
        concurrentSessionControlAuthenticationStrategy.setExceptionIfMaximumExceeded(false);

        ChangeSessionIdAuthenticationStrategy changeSessionIdAuthenticationStrategy =
                new ChangeSessionIdAuthenticationStrategy();


        CompositeSessionAuthenticationStrategy strategy =
                new CompositeSessionAuthenticationStrategy(
                        List.of(changeSessionIdAuthenticationStrategy,
                                concurrentSessionControlAuthenticationStrategy,
                                registerSessionAuthenticationStrategy)
                );

        filter.setAuthenticationSuccessHandler(new AjaxSuccessHandler());
        filter.setAuthenticationFailureHandler(new AjaxFailureHandler());
        filter.setAllowSessionCreation(true);
        filter.setSessionAuthenticationStrategy(strategy);
        filter.setSecurityContextRepository(repository);
        filter.setAuthenticationManager(authenticationManager);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
    public static CustomDsl customDsl(SessionRegistry sessionRegistry){
        return new CustomDsl(sessionRegistry);
    }
}
