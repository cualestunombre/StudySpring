package hello.demo.web.frontcontroller.v4;

import hello.demo.domain.Member;
import hello.demo.repository.MemberRepository;
import hello.demo.web.frontcontroller.ModelView;
import hello.demo.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public String process(Map<String,String> paramMapm,Map<String,Object> model){
        List<Member> members = memberRepository.findAll();
        model.put("members",members);
        return "members";
    }
}
