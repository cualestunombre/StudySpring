package hello.jdbc.repository;

import hello.jdbc.domain.Member;

public interface MemberRepository { //인터페이스화하였음
    Member save(Member member);
    Member findById(String memberId);
    void update(String memberId, int money);
    void delete(String memberId);
}
