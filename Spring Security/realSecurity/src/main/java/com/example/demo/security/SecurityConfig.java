package com.example.demo.security;

import com.example.demo.domain.dto.SimpleJsonResponse;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.handler.CustomAuthenticationFailureHandler;
import com.example.demo.security.handler.CustomAuthenticationSuccessHandler;
import com.example.demo.security.provider.AjaxAuthenticationProvider;
import com.example.demo.security.provider.CustomAuthenticationProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Configuration
public class SecurityConfig {

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }
    @Bean
    public ConcurrentSessionFilter concurrentSessionFilter(){
        return new ConcurrentSessionFilter(sessionRegistry());
    }


    @Bean
    public AuthenticationProvider customAuthenticationProvider(UserDetailsService userDetailsService){
        return new CustomAuthenticationProvider(userDetailsService,passwordEncoder());
    }

    @Bean
    public AuthenticationProvider ajaxAuthenticationProvider(UserDetailsService userDetailsService){
        return new AjaxAuthenticationProvider(userDetailsService,passwordEncoder());
    }






    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    // 설정의 커스터 마이징을 도와 줌
    public WebSecurityCustomizer webSecurityCustomizer(){
        return
                // 아예 해당경로를 모든 보안필터를 거치지 않도록 함
                (web)->web.ignoring().requestMatchers("/css/**","js/**","images/**");
    }
    @Order(1)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserRepository userRepository,UserDetailsService userDetailsService,
                                                   AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> detailsSource) throws Exception {
        http
                .securityMatcher(new AntPathRequestMatcher("/**"))
                .authenticationProvider(customAuthenticationProvider(userDetailsService))
                .userDetailsService(userDetailsService)
                .authorizeHttpRequests(auth->{
                    auth
                            .requestMatchers("/").permitAll()
                            .requestMatchers("/users").permitAll()
                            .requestMatchers("/mypage").hasRole("USER")
                            .requestMatchers("/error").permitAll()
                            .requestMatchers("/messages").hasRole("MANAGER")
                            .requestMatchers("/login").permitAll()
                            .requestMatchers("/logout").permitAll()
                            .requestMatchers("/config").hasRole("ADMIN")
                            .requestMatchers("/test").permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin(login->{
                    login.loginPage("/login");
                    login.loginProcessingUrl("/login");
                    login.usernameParameter("username");
                    login.passwordParameter("password");
                    login.failureHandler(new CustomAuthenticationFailureHandler());
                    login.successHandler(new CustomAuthenticationSuccessHandler());
                    login.authenticationDetailsSource(detailsSource);
                })
                .sessionManagement(s->{
                    s.sessionFixation().migrateSession();
                    s.maximumSessions(1).maxSessionsPreventsLogin(false).expiredUrl("/login");
                })
                .exceptionHandling(ex->{
                   ex.accessDeniedHandler(new AccessDeniedHandler() {
                       @Override
                       public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                            response.sendRedirect("/denied?exception="+accessDeniedException.getMessage());
                       }
                   }) ;
                });

        return http.build();
    }

    @Order(0)
    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {
        http.apply(CustomDsl.customDsl(sessionRegistry()));
        http
                .securityMatcher(new AntPathRequestMatcher("/api/**"))
                .authenticationProvider(ajaxAuthenticationProvider(userDetailsService))
                .authorizeHttpRequests(auth->{
                    auth
                            .requestMatchers("/api/messages").hasRole("MANAGER")
                            .requestMatchers("/api/login").permitAll()
                            .anyRequest()
                            .authenticated();
                })
                .sessionManagement(
                        s->{
                            s.maximumSessions(1).expiredSessionStrategy(new SessionInformationExpiredStrategy() {
                                @Override
                                public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
                                    event.getResponse().getWriter().write("Session Expired");
                                }
                            });
                        }
                )
                .exceptionHandling(ex->{
                   ex.accessDeniedHandler(new AccessDeniedHandler() {
                       private final ObjectMapper objectMapper = new ObjectMapper();
                       @Override
                       public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                           int code = 403;
                           response.getWriter().write(objectMapper.writeValueAsString(new SimpleJsonResponse(code,"Access Denied")));

                       }
                   });
                   ex.authenticationEntryPoint(new AuthenticationEntryPoint() {
                       private final ObjectMapper objectMapper = new ObjectMapper();
                       @Override
                       public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                           int code = 401;
                           response.getWriter().write(objectMapper.writeValueAsString(new SimpleJsonResponse(code,"Need authentication")));
                       }
                   });
                })
                ;
        return http.build();
    }
}
