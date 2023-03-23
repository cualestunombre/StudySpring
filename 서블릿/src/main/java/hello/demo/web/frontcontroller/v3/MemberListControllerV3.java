package hello.demo.web.frontcontroller.v3;

import hello.demo.domain.Member;
import hello.demo.repository.MemberRepository;
import hello.demo.web.frontcontroller.ModelView;
import java.util.*;

public class MemberListControllerV3 implements ControllerV3{
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String,String> paramMap){
        List<Member> members = memberRepository.findAll();

        ModelView mv = new ModelView("members");
        mv.getModel().put("members",members);
        return mv;
    }
}
