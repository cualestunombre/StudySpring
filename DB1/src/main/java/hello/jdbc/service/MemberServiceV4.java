package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
public class MemberServiceV4 {
    private final MemberRepository memberRepository;
    //트랜잭션 어노테이션을 쓰면, 트랜잭션 매니져(JpaTransactionManager)와 DataSource를 자동제공받는다.
    // 스프링이 DataSource와 트랜잭션 매니져를 자동적으로 등록해주기 때문이다.
    @Transactional
    public void accountTransfer(String fromId, String toId, int money){
        bizLogic(fromId,toId,money);
    }
    private void bizLogic(String fromId,String toId,int money){
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);
        memberRepository.update(fromId,fromMember.getMoney()-money);
        validate(toMember);
        memberRepository.update(toId,toMember.getMoney()+money);
    }
    private void validate(Member toMember){
        if (toMember.getMemberId().equals("ex")) throw new IllegalStateException("이체중 예외발생");
    }
}
