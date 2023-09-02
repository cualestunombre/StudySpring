package hello.logic.component;

import hello.logic.authentication.MembernamePasswordAuthentication;
import hello.logic.authentication.OtpAuthentication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;



@RequiredArgsConstructor
@Getter
@Setter
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    public final AuthenticationManager manager;

    private final String signingKey;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String name = request.getHeader("name");
        String password = request.getHeader("password");
        String code = request.getHeader("code");
        System.out.println(signingKey);
        if(!StringUtils.hasText(code)){
           Authentication a =
                   new MembernamePasswordAuthentication(name,password);
                manager.authenticate(a);
        }else{
            Authentication a =
                    new OtpAuthentication(name,code);

            a = manager.authenticate(a);

            SecretKey key = Keys.hmacShaKeyFor(
                    signingKey.getBytes(StandardCharsets.UTF_8)
            );

            String jwt = Jwts.builder()
                    .setClaims(Map.of("name",name))
                    .signWith(key)
                    .compact();
            response.setHeader("Authorization",jwt);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return !request.getServletPath().equals("/login");
    }
}
