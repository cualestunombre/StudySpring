package hello.demo.web.frontcontroller.v3;

import hello.demo.repository.MemberRepository;
import hello.demo.web.frontcontroller.ModelView;
import java.util.*;
import hello.demo.domain.Member;

public class MemberSaveControllerV3 implements ControllerV3{
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String,String> paramMap){
        String username = paramMap.get("name");
        int age = Integer.parseInt(paramMap.get("age"));
        Member member = new Member(username,age);
        memberRepository.save(member);
        List<Member> members = memberRepository.findAll();
        ModelView mv = new ModelView("members");
        mv.getModel().put("members",members);
        return mv;

    }
}
