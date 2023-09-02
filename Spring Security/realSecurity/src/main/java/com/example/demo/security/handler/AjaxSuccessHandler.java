package com.example.demo.security.handler;

import com.example.demo.domain.dto.SimpleJsonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import java.io.IOException;

public class AjaxSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        int code = 200;
        response.setStatus(code);
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if (savedRequest == null){
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleJsonResponse(code,"/")));
        }else{
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleJsonResponse(code,savedRequest.getRedirectUrl())));

        }


    }
}
