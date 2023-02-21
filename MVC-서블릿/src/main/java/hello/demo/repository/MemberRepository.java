package hello.demo.repository;
import hello.demo.domain.Member;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
public interface MemberRepository{
    Member save(Member member);
    List<Member> findAll();
    Member findById(int id);
}
