package hello.small.repository;

import hello.small.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional
@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findUserByUsername(String u);
}
