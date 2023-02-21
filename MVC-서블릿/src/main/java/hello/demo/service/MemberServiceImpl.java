package hello.demo.service;

import hello.demo.domain.Member;
import hello.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository ;
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }
    @Override
    public Member join(Member member){
        memberRepository.save(member);
        return member;
    }
    @Override
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    @Override
    public Member findOne(int id){
        return memberRepository.findById(id);
    }
}
