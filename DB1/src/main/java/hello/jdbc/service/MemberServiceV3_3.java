package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class MemberServiceV3_3 {
    private final MemberRepositoryV3 memberRepository;

    //트랜잭션 어노테이션에는 데이터 소스가 주입돤 트랜잭션 매니져가 필요한데, 트랜잭션 어노테이션을 사용하면 기본적으로 주입관계가 해소된 트랜잭션 매니져를
    //스프링이 알아서 주입함
    @Transactional
    public void accountTransfer(String fromId, String toId, int money) throws SQLException{
        bizLogic(fromId,toId,money);
    }
    private void bizLogic(String fromId,String toId, int money) throws SQLException{
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);
        memberRepository.update(fromId,fromMember.getMoney()-money);
        validation(toMember);
        memberRepository.update(toId,toMember.getMoney()+money);

    }
    private void validation(Member toMember){
        if(toMember.getMemberId().equals("ex")){
            throw new IllegalStateException("이체중 예외발생");
        }
    }
}
