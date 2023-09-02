package hello.logic.config;

import hello.logic.component.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class ProjectConfig{
    private final CustomEntryPoint customEntryPoint;

    @Value("${jwt.signing.key}")
    private String key;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   OtpAuthenticationProvider otpAuthenticationProvider, MembernamePassWordAuthenticationProvider membernamePassWordAuthenticationProvider,
                                                   JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
                .authenticationProvider(otpAuthenticationProvider)
                .authenticationProvider(membernamePassWordAuthenticationProvider)
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(req->{
                    req.anyRequest().authenticated();
                })
                .exceptionHandling(c->{
                    c.authenticationEntryPoint(customEntryPoint);
                })
                .apply(CustomDsl.customDsl(key));
        return http.build();
    }



}
