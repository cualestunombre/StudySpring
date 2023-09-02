package hello.logic.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class MembernamePasswordAuthentication extends UsernamePasswordAuthenticationToken {
    public MembernamePasswordAuthentication(
            Object principal,
            Object credentials,
            Collection<? extends GrantedAuthority> authorities
    ){
        super(principal,credentials,authorities);
    }

    public MembernamePasswordAuthentication(
            Object principal,
            Object credentials
    ){
        super(principal, credentials);
    }


}
