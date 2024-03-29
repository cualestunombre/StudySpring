package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class MemberServiceV3 {
    private final PlatformTransactionManager transactionManager; // 트랜잭션 매니져가 추가 됨
    private final MemberRepositoryV3 memberRepository;

    public void accountTransfer(String fromId,String toId, int money) throws SQLException{
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition()); //관리 시작

        try{
            bizLogic(fromId,toId,money);
            transactionManager.commit(status);
        }catch(Exception e){
            transactionManager.rollback(status);
            throw new IllegalStateException(e);
        }
    }
    private void bizLogic(String fromId,String toId,int money) throws SQLException{
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);

        memberRepository.update(fromId, fromMember.getMoney()-money);
        validation(toMember);
        memberRepository.update(toId,toMember.getMoney()+money);
    }
    private void validation(Member toMember){
        if(toMember.getMemberId().equals("ex")){
            throw new IllegalStateException("이체중 예외 발생");
        }
    }
}
