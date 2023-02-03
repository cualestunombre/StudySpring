package hello.demo.repository;
import hello.demo.domain.Member;
import java.util.List;
import java.util.Optional;
public interface MemberRepository{
    Member save(Member member);
    Optional<Member> findById(Long id); //null을 Optional로 감싸는 것을 선호하는 추세임
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
