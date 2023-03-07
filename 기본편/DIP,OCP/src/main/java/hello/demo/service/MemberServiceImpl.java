package hello.demo.service;

import hello.demo.domain.Member;
import hello.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MemberServiceImpl implements MemberService{
    private MemberRepository memberRepository ;
    private Member member;
    public MemberRepository getMemberRepository() {
        return this.memberRepository;
    }
    @Autowired
    public void forTest(@Nullable Member member){
        System.out.println(member+"sdsd");
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
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
