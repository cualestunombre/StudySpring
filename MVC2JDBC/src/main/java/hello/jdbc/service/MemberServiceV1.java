package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;

@RequiredArgsConstructor
public class MemberServiceV1 {
    private final MemberRepositoryV1 memberRepositoryV1; // 리포지터리를 받음

    public void accountTransfer(String fromId,String toId,int money) throws SQLException {
        Member fromMember = memberRepositoryV1.findById(fromId);
        Member toMember = memberRepositoryV1.findById(toId);
        memberRepositoryV1.update(fromId, fromMember.getMoney()-money);
        validation(toMember); // 예외가 발생
        memberRepositoryV1.update(toId,toMember.getMoney()+money); // 예외가 발생하면 쿼리가 실행되지 못함

    }
    private void validation(Member toMember){
        if(toMember.getMemberId().equals("ex")){
            throw new IllegalStateException("이체중 예외 발생");
        }
    }
}
