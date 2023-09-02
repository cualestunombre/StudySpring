package hello.ss.config;

import hello.ss.details.DummyUser;
import hello.ss.filter.CsrfTokenLogger;
import hello.ss.filter.StaticKeyAuthenticationFilter;
import hello.ss.services.InMemoryUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.Arrays;
import java.util.List;




@EnableAsync // 설정 클래스위에 위치하는 것이 좋다
@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class HttpBasicConfig implements WebMvcConfigurer {



    // 기존의 구현체 사용
    public UserDetailsService userDetailsServiceV1(){
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
        UserDetails user = new DummyUser("seok","12345","read");
        userDetailsService.createUser(user);

        return userDetailsService;
    }

    //사용자 구현
    @Bean
    public UserDetailsService userDetailsServiceV2(){
        UserDetails admin = new DummyUser("admin","12345","ROLE_ADMIN");
        UserDetails u = new DummyUser("seok","12345","read");
        UserDetails u2 = new DummyUser("homin","12345","write");
        UserDetails u3 = new DummyUser("jungwon","12345","read");
        List<UserDetails> users = List.of(u,u2,u3,admin);
        return new InMemoryUserDetailService(users);
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8030")
                .allowedMethods("GET", "POST","OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true).maxAge(0);


        // Add more mappings...
    }



    // UserDetailService를 등록하면 반드시 PasswordEncoder도 다시 재등록해야 한다
    @Bean
    public PasswordEncoder passwordEncoder(){
        // 운영단계에서는 사용해서는 안된다!! 평문 비교
        return NoOpPasswordEncoder.getInstance();
    }

    // SecurityContext 설정
    @Bean
    public InitializingBean initializingBean(){
        return ()-> SecurityContextHolder.setStrategyName(
                SecurityContextHolder.MODE_THREADLOCAL
        );
    }
    //기본은 베이직 인증이다!!
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(c->{
                    c.realmName("OTHER");
                    c.authenticationEntryPoint(new CustomEntryPoint());}
                )
                .authorizeHttpRequests((auth)->{
                    auth.anyRequest().authenticated();
                });
        return http.build();
    }
    //그러나, form 인증도 간편하게 사용할 수 있다
    @Bean
    public SecurityFilterChain formFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(c->{
                    c.defaultSuccessUrl("/home",true);
                    c.failureUrl("/login");
                })
                .addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class)
                .csrf(c->{
                    c.ignoringRequestMatchers("/ciao");
                })
                .authorizeHttpRequests((auth)->{
                   auth
                           .requestMatchers("/admin/**")
                           .hasRole("ADMIN")
                           .requestMatchers("/video/*/en")
                           .hasRole("ADMIN")
                            .anyRequest()
                            .permitAll();
                });

        return http.build();
    }



}
