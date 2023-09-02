package hello.Oauth2Resource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.
                securityMatcher("/articles/**")
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers("/articles/**")
                            .hasAuthority("SCOPE_articles.read");
                })
                .oauth2ResourceServer(c->{
                    c.jwt(Customizer.withDefaults());
                });
        return http.build();
    }
}
