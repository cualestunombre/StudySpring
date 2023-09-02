package hello.app.pointcut;

import hello.app.exam.ExamService;
import hello.app.exam.aop.RetryAspect;
import hello.app.exam.aop.TraceAspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import({TraceAspect.class, RetryAspect.class})
public class ExamTest {
    @Autowired
    ExamService examService;

    @Test
    void test(){
        for(int i = 0; i < 5 ; i++){
            examService.request("data"+String.valueOf(i));
        }
    }


}
