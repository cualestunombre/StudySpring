package hello.demo.web.frontcontroller.v4;

import hello.demo.domain.Member;
import hello.demo.repository.MemberRepository;
import hello.demo.web.frontcontroller.ModelView;
import hello.demo.web.frontcontroller.v3.ControllerV3;

import java.util.Map;
import java.util.*;

public class MemberSaveControllerV4 implements ControllerV4 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public String process(Map<String,String> paramMap, Map<String,Object> model){
        String username = paramMap.get("name");
        int age = Integer.parseInt(paramMap.get("age"));
        Member member = new Member(username,age);
        memberRepository.save(member);
        List<Member> members = memberRepository.findAll();
        model.put("members",members);
        return "members";

    }
}
