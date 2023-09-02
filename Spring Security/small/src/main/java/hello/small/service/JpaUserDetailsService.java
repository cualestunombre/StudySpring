package hello.small.service;

import hello.small.details.CustomUserDetails;
import hello.small.domain.Member;
import hello.small.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class JpaUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String membername){
        Member m = memberRepository
                .findUserByUsername(membername)
                .orElseThrow(()-> new UsernameNotFoundException("no user match"));
        return new CustomUserDetails(m);
    }



}
