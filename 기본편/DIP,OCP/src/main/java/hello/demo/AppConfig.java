package hello.demo;
import hello.demo.domain.Member;
import hello.demo.repository.MemberRepository;
import hello.demo.repository.MemoryMemberRepository;
import hello.demo.service.*;

public class AppConfig {
    private MemberService memberService;
    private OrderService orderSerivce;
    public AppConfig(){
        this.memberService= new MemberServiceImpl(new MemoryMemberRepository());
        this.orderSerivce= new OrderServiceImpl();
    }
}
