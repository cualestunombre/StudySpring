package hello.ss.config;

import jakarta.servlet.http.HttpServlet;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

// 인증 범위를 설정할 수 있다
// 책의 WebbSecurityConfigureAdapter 는 더이상 쓰이지 않는다

