package hello.small.config;

import hello.small.filter.CsrfTokenLogger;
import hello.small.repository.CustomCsrfTokenRepository;
import hello.small.repository.JpaTokenRepository;
import hello.small.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ProjectConfig {
    private final JpaTokenRepository jpaTokenRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http
               .csrf(csrf->{
                   csrf.csrfTokenRepository(csrfTokenRepository());
                   csrf.csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler());
                   csrf.disable();
               })
               .formLogin(c->{
                 c.defaultSuccessUrl("/main");
                 c.loginPage("/login");
                 c.permitAll();

               })
               .logout(c->{
                   c.logoutUrl("/logout");
               })
               .authorizeHttpRequests(auth->{
                  auth
                          .requestMatchers()
                          .anyRequest().authenticated();
               });
        return http.build();
    }

    @Bean
    public CsrfTokenRepository csrfTokenRepository(){
        return new CustomCsrfTokenRepository(jpaTokenRepository);
    }

}
