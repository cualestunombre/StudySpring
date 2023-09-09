package com.example.demo.security.details;

import com.example.demo.domain.entity.Account;
import com.example.demo.domain.entity.Role;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = userRepository
                .findAccountByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Not matched user found"));

        Set<Role> roles = account.getUserRoles();
        Set<String> userRoles = new HashSet<>();

        for(Role role:roles){
            Role curRole = role;
            while (curRole != null){
                userRoles.add(curRole.getRoleName());
                curRole = curRole.getParentRole();
            }
        }

        Collection<? extends GrantedAuthority> authorities =
                userRoles.stream()
                        .map(e->(GrantedAuthority)()->{return e;})
                        .collect(Collectors.toList());

        return new JpaUserDetails(account,authorities);
    }
}
