package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.SQLException;

@Slf4j
public class MemberServiceV3_2 {
    private final TransactionTemplate txTemplate; // 트랜잭션 매니져를 갖고 있음
    private final MemberRepositoryV3 memberRepository;

    public MemberServiceV3_2(PlatformTransactionManager transactionManager, MemberRepositoryV3 memberRepository){
        this.txTemplate=new TransactionTemplate(transactionManager);
        this.memberRepository=memberRepository;
    }

    public void accountTransfer(String fromId, String toId, int money) {
        txTemplate.executeWithoutResult(status->{
            try{
                bizLogic(fromId,toId,money);
            }catch(SQLException e){ //체크 예외는 롤백 없이 커밋한다, 언체크 예외만 롤백 한다
                throw new IllegalStateException(e); //언체크 예외(런타임 예외)
            }
        });
    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException{
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);
        memberRepository.update(fromId,fromMember.getMoney()-money);
        validation(toMember);
        memberRepository.update(toId,toMember.getMoney()+money);


    }

    private void validation(Member toMember){
        if(toMember.getMemberId().equals("ex")){
            throw new IllegalStateException("이체중 예외 발생");
        }
    }



}
