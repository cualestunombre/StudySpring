package hello.demo;
import hello.demo.domain.Hi;
import hello.demo.domain.Hiinter;
import hello.demo.domain.Member;
import hello.demo.repository.MemberRepository;
import hello.demo.repository.MemoryMemberRepository;
import hello.demo.service.NetworkService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class
Appconfig {
    @Bean
    public Hiinter hiinter(){
        return new Hi(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    @Bean
    public NetworkService networkService(){
        NetworkService networkService = new NetworkService();
        networkService.setUrl("www.naver.com");
        return networkService;
    }
}
