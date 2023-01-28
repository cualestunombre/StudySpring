package hello.demo.service;

import hello.demo.domain.Member;
import hello.demo.repository.MemberRepository;
import hello.demo.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class MemberService {
    private final MemberRepository memberRepository ;
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName()).ifPresent(m->{throw new IllegalStateException("이미 존재하는 회원입니다");});
    }
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
