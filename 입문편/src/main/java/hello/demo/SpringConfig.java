package hello.demo;
import hello.demo.repository.JpaMemberRepository;
import hello.demo.repository.JdbcMemberRepository;
import hello.demo.repository.JdbcTemplateMemberRepository;
import hello.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
@Configuration
public class SpringConfig {
    private final EntityManager em;
    @Autowired
    @Bean
    public MemberRepository memberRepository(){
        // return new MemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }

}
