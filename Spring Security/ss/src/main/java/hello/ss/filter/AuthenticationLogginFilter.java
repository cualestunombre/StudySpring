package hello.ss.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AuthenticationLogginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var httpRequest = (HttpServletRequest)request;
        var httpResponse = (HttpServletResponse)response;

        var requestId =
                httpRequest.getHeader("Request-Id");
        log.info("Successfully authenticated request with id "+requestId);

        filterChain.doFilter(request,response);
    }
}
