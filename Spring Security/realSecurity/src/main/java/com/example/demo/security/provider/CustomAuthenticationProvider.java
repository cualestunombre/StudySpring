package com.example.demo.security.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails jpaUserDetails =
                userDetailsService.loadUserByUsername(authentication.getName());
        if (passwordEncoder.matches((CharSequence) authentication.getCredentials(),jpaUserDetails.getPassword())){
//            FormWebAuthenticationDetails details =
//                    (FormWebAuthenticationDetails) authentication.getDetails();
//            if (!("1111".equals(details.getSecretKey()))){
//                throw new InternalAuthenticationServiceException("Insufficient Info");
//
//            }

            return UsernamePasswordAuthenticationToken.authenticated(
                    jpaUserDetails,null,jpaUserDetails.getAuthorities()
            );

        }else{
            throw new BadCredentialsException("Login failed");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
