package hello.small.service;

import hello.small.details.CustomUserDetails;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationProviderService implements AuthenticationProvider {
    private final JpaUserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication){
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        CustomUserDetails user =
                    userDetailsService.loadUserByUsername(username);
        switch (user.getMember().getAlgorithm()){
            case BCRYPT:
                return checkPassword(user,password,bCryptPasswordEncoder);
            case SCRYPT:
                return null;
        }
        throw new BadCredentialsException("Bad Credentials");
    }



    @Override
    public boolean supports(Class<?> clazz){
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(clazz);
    }

    private Authentication checkPassword(CustomUserDetails user,
                                         String rawPassword,
                                         PasswordEncoder encoder){
        if(encoder.matches(rawPassword,user.getPassword())){
            return new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword(),
                    user.getAuthorities()
            );
        }else{
            throw new BadCredentialsException("Bad Credentials");
        }
    }
}
