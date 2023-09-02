package hello.inflearn.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import java.io.IOException;

@Configuration
public class ProjectConfig {


    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails u1 = User
                .withUsername("seok")
                .password("{noop}1234")
                .roles("USER")
                .build();
        UserDetails u2 = User
                .withUsername("sys")
                .password("{noop}1234")
                .roles("SYS")
                .build();
        UserDetails u3 = User
                .withUsername("admin")
                .password("{noop}1234")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(u1,u2,u3);


    }

    // 경로 별 다른 설정을 적용할 수 있음
    @Order(1)
    @Bean
    public SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception{
        http.securityMatcher(new AntPathRequestMatcher("/*/*/*"))
                .authorizeHttpRequests(auth->{
                    auth.anyRequest().permitAll();
                });
        return http.build();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher(new AntPathRequestMatcher("/**/"))
                .userDetailsService(userDetailsService())
                .formLogin(login->{
                    login.loginPage("/home");
                    login.defaultSuccessUrl("/home",true);
                    login.failureUrl("/home?error=wrong");
                    login.loginProcessingUrl("/login");
                    login.usernameParameter("username");
                    login.passwordParameter("password");

                })
                .sessionManagement(s->{
                    s.maximumSessions(1).maxSessionsPreventsLogin(false)
                                    .expiredUrl("/home?error=other");
                    s.sessionFixation().migrateSession();
                })
                .anonymous(Customizer.withDefaults())
                .rememberMe(rm->{
                    rm.rememberMeParameter("remember");
                    rm.tokenValiditySeconds(3600);
                    rm.alwaysRemember(false);
                    rm.userDetailsService(userDetailsService());
                })
                .logout(logout->{
                    logout.logoutUrl("/logout");
                    logout.logoutSuccessUrl("/home");
                    logout.deleteCookies("JSESSIONID","remember");

                })
                .authorizeHttpRequests(auth->{
                    auth
                            .requestMatchers("/home")
                            .permitAll()
                            .requestMatchers("/login")
                                    .permitAll()
                            .requestMatchers("/logout")
                            .permitAll()
                            .requestMatchers("/anonymous")
                            .hasRole("ANONYMOUS")
                            .requestMatchers("/user")
                            .hasRole("USER")
                            .requestMatchers("/admin/pay")
                            .hasRole("ADMIN")
                            .requestMatchers("/admin/**")
                            .access((authentication, object) -> {
                                authentication.get().getAuthorities().forEach(System.out::println);
                                long count = authentication
                                        .get().getAuthorities()
                                        .stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .filter(e -> e.equals("ROLE_ADMIN") || e.equals("ROLE_SYS"))
                                        .count();
                                return count >= 1L ? new AuthorizationDecision(true): new AuthorizationDecision(false);
                            })
                            .requestMatchers("/**")
                            .authenticated();
                }).exceptionHandling(e->{
                    e.authenticationEntryPoint(new AuthenticationEntryPoint() {
                        @Override
                        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                            response.sendRedirect("/home?error=auth");
                        }
                    });
                });
        return http.build();
    }
}
