package hello.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(){
        var c =clientRegistration();
        return new InMemoryClientRegistrationRepository(c);
    }
    private ClientRegistration clientRegistration(){
        return CommonOAuth2Provider.GITHUB
                .getBuilder("github")
                .clientId("b1f32432bd8fbb2e9ed7")
                .clientSecret("ac341672fea0854342a7b27f0ca039c42f37d158")
                .build();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2Login(c->{
            c.clientRegistrationRepository(clientRegistrationRepository());
        })
                .authorizeHttpRequests(c->{
                    c.anyRequest().authenticated();
                });
        return http.build();
    }
}
