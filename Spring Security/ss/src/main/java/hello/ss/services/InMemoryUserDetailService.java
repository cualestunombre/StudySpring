package hello.ss.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
@RequiredArgsConstructor
public class InMemoryUserDetailService implements UserDetailsService {

    private final List<UserDetails> users;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream()
                .filter((e)->e.getUsername().equals(username))
                .findFirst()
                .orElseThrow(()->new UsernameNotFoundException("User not found"));

    }
}
