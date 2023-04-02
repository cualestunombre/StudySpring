package hello.demo;
import hello.demo.repository.JpaMemberRepository;
import hello.demo.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {


    @Bean
    public MemberRepository memberRepository(){
        // return new MemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(null);
    }
    @Bean
    public int returnInt(){
        return new Integer(5);
    }

}
