package hello.logic.component;

import hello.logic.authentication.MembernamePasswordAuthentication;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class MembernamePassWordAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private  AuthenticationServerProxy proxy;

    @Override
    public Authentication authenticate(Authentication authentication){
        String name = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        proxy.sendAuth(name,password);

        return new UsernamePasswordAuthenticationToken(name, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MembernamePasswordAuthentication.class.isAssignableFrom(authentication);
    }
}
