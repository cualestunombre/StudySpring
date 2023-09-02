package hello.logic.config;

import com.sun.net.httpserver.HttpServer;
import hello.logic.component.InitialAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {
    private CustomDsl(String key){
        this.key = key;
    }
    private String key;
    @Override
    public void configure(HttpSecurity http) throws Exception{
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.addFilterAt(new InitialAuthenticationFilter(authenticationManager,key), BasicAuthenticationFilter.class);
    }
    public static CustomDsl customDsl(String key){
        return new CustomDsl(key);
    }
}
