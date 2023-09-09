package com.example.demo.security.filter;

import com.example.demo.security.details.CustomUserDetailsService;
import com.example.demo.security.details.JpaUserDetails;
import com.example.demo.security.token.AjaxAuthenticationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class AuthoritiesRefreshFilter extends OncePerRequestFilter {

    @Autowired
    CustomUserDetailsService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 현재 사용자 인증 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 인증되지 않은 사용자 또는 익명 사용자인 경우 필터 체인 진행
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            filterChain.doFilter(request, response);
            return;
        }

        // 현재 사용자의 UserDetails 가져오기
        JpaUserDetails userDetails = (JpaUserDetails) authentication.getPrincipal();

        // 데이터베이스에서 UserDetails 다시 가져오기 (예: 권한이 변경된 경우를 처리)
        JpaUserDetails findDetails = (JpaUserDetails) service.loadUserByUsername(userDetails.getUsername());

        // 새로운 AuthenticationToken 생성
        AjaxAuthenticationToken token = new AjaxAuthenticationToken(findDetails, null, findDetails.getAuthorities());

        // 새로운 Authentication으로 SecurityContextHolder 업데이트
        SecurityContextHolder.getContext().setAuthentication(token);

        // 필터 체인 계속 진행
        filterChain.doFilter(request, response);
    }
}
