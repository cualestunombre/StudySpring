package hello.demo.service;

import hello.demo.domain.Member;
import hello.demo.repository.MemberRepository;

import java.util.*;

public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository ;

    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }
    @Override
    public double join(Member member){
        memberRepository.save(member);
        return member.getId();
    }
    @Override
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    @Override
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
