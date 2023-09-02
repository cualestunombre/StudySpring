package hello.springtx.propagation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class LogRepository {
    private final EntityManager em;

    // 새 트랜잭션 --> 독립적이다
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Log message){
        log.info("log 저장");
        em.persist(message);
        if(message.getMessage().contains("로그예외")){
            log.info("log 저장시 예외 발생");
            throw new RuntimeException("예외 발생"); //런타임 에러라서 트랜잭션에 묶이면 그냥 다 롤백
        }
    }
    public Optional<Log> find(String message){
        return em.createQuery("select l from Log l where l.message = :message",Log.class)
                .setParameter("message",message)
                .getResultList().stream().findAny();
    }
}
