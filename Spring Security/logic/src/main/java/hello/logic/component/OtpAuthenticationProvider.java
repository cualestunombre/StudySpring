package hello.logic.component;

import hello.logic.authentication.OtpAuthentication;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class OtpAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private  AuthenticationServerProxy proxy;

    @Override
    public Authentication authenticate(Authentication authentication){
        String name = authentication.getName();
        String code = String.valueOf(authentication.getCredentials());

        boolean result = proxy.sendOTP(name,code);

        if(result){
            return new OtpAuthentication(name,code);
        }
        else{
            throw  new BadCredentialsException("Bad credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthentication.class.isAssignableFrom(authentication);
    }


}
