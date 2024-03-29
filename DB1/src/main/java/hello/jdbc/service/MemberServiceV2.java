package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {
    private final DataSource dataSource; // 히카리로 받은 커넥션 풀임
    private final MemberRepositoryV2 memberRepository;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException{
        Connection con = dataSource.getConnection(); // 전달받은 커넥션
        try{
            con.setAutoCommit(false); //autocommit을 해제함
            bizLogic(con,fromId,toId,money);
            con.commit();
        }catch(Exception e){ //예외시 롤백해버림
            con.rollback();
            throw new IllegalStateException(e);
        }finally {
            release(con);
        }
    }
    private void bizLogic(Connection con,String fromId,String toId, int money) throws SQLException{
        Member fromMember = memberRepository.findById(con,fromId);
        Member toMember = memberRepository.findById(con,toId);

        memberRepository.update(con,fromId, fromMember.getMoney()-money);
        validation(toMember);
        memberRepository.update(con,toId,toMember.getMoney()+money);

    }
    private void validation(Member member){
        if(member.getMemberId().equals("ex")){
            throw new IllegalStateException("이체중 예외발생");
        }
    }
    private void release(Connection con){
        if(con!=null){
            try{
                con.setAutoCommit(true); //풀을 고려해서, 커넥션의 설정이 공유 됨
                con.close();
            }catch(Exception e){
                log.info("error",e);
            }
        }
    }
}
