package com.example.demo.security.filter;

import com.example.demo.domain.dto.AccountDto;
import com.example.demo.security.token.AjaxAuthenticationToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import java.io.IOException;
public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public AjaxLoginProcessingFilter(AuthenticationManager authenticationManager){
        super(new AntPathRequestMatcher("/api/login"));
        super.setAuthenticationManager(authenticationManager);

    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!isAjax(request)){
            throw new IllegalStateException("Authentication is not supported");
        }

        AccountDto accountDto = objectMapper.readValue(request.getReader(), AccountDto.class);
        if (!StringUtils.hasText(accountDto.getUsername())||!StringUtils.hasText(accountDto.getPassword())){
            throw new IllegalArgumentException("Username or Password is empty");
        }
        AjaxAuthenticationToken token =
                new AjaxAuthenticationToken(accountDto.getUsername(),accountDto.getPassword());


        return getAuthenticationManager().authenticate(token);
    }

    private boolean isAjax(HttpServletRequest request){
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            return true;
        }
        return false;
    }
}
