package hello.demo.domain;

import hello.demo.repository.MemberRepository;

public class Hi implements Hiinter{
    private final MemberRepository m;
    public Hi(MemberRepository m){
        this.m=m;
    }
}
