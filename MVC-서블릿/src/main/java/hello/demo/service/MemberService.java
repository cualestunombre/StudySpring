package hello.demo.service;

import hello.demo.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    public Member join(Member member);

    public List<Member> findMembers();

    public Member findOne(int id);
}
