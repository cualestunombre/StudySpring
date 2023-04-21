package hello.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

@Slf4j
@SpringBootTest
public class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LogRepository logRepository;
    /*
    MemberService @Transactional:OFF
    MemberRepository @Transactional:ON
    LogRepository @Transactional:ON
     */

//    @Test
    void outerTxOff_success(){ //모든 데이터 정상 저장
        String username="outerTxOff_success";

        memberService.joinV1(username);

        Assertions.assertTrue(memberRepository.find(username).isPresent());
        Assertions.assertTrue(memberRepository.find(username).isPresent());
    }
//    @Test
    void outerTxOff_faile(){
        String username="로그예외_outerTxoff_fail";
        assertThatThrownBy(()->memberService.joinV1(username)).isInstanceOf(RuntimeException.class);

        Assertions.assertTrue(memberRepository.find(username).isPresent());
        Assertions.assertTrue(logRepository.find(username).isEmpty());
    }
    /*
    MemberService @Transactional:ON
    MemberRepository @Transactional:OFF
    LogRepository @Transactional:OFF
     */
//    @Test
    void singleTx(){
        String username = "singleTx";

        memberService.joinV1(username);

        Assertions.assertTrue(memberRepository.find(username).isPresent());
        Assertions.assertTrue(logRepository.find(username).isPresent());


    }
//    @Test
    void singleTxFail(){
        String username = "로그예외_singleTx";
        assertThatThrownBy(()-> memberService.joinV1(username)).isInstanceOf(RuntimeException.class);

        Assertions.assertTrue(memberRepository.find(username).isEmpty());
        Assertions.assertTrue(logRepository.find(username).isEmpty());
    }
    /*
    MemberService @Transactional:ON
    MemberRepository @Transactional:ON
    LogRepository @Transactional:ON
     */
//    @Test
    void outerTxOn_success(){
        String username = "outerTxOn_success";
        memberService.joinV1(username);

        Assertions.assertTrue(memberRepository.find(username).isPresent());
        Assertions.assertTrue(logRepository.find(username).isPresent());
    }
   @Test
    void outerTxOn_faile(){
        String username = "로그예외_outerTxOn_fail";
        assertThatThrownBy(()-> memberService.joinV1(username)).isInstanceOf(RuntimeException.class); //변환 됨

        Assertions.assertTrue(memberRepository.find(username).isEmpty());
        Assertions.assertTrue(logRepository.find(username).isEmpty());
    }
    /*
    MemberService @Transactional:ON
    MemberRepository @Transactional:ON
    LogRepository @Transactional:ON Exception
     */
//    @Test
    void recoverException_fail(){
        String username = "로그예외_recoverException_fail";

        assertThatThrownBy(()-> memberService.joinV2(username))
                .isInstanceOf(UnexpectedRollbackException.class);
        Assertions.assertTrue(memberRepository.find(username).isEmpty());
        Assertions.assertTrue(logRepository.find(username).isEmpty());
    }
    /*
    MemberService @Transactional:ON
    MemberRepository @Transactional:ON
    LogRepository @Transactional REQUIRES_NEW
     */
    @Test
    void recoverException_success(){
        String username = "로그예외_recoverException_success";

        memberService.joinV2(username);

        Assertions.assertTrue(memberRepository.find(username).isPresent());
        Assertions.assertTrue(logRepository.find(username).isEmpty());
    }


}
