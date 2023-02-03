package hello.demo.service;

import hello.demo.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    public double join(Member member);

    public List<Member> findMembers();

    public Optional<Member> findOne(Long memberId);
}
