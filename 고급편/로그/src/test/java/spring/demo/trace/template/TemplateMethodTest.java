package spring.demo.trace.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring.demo.trace.template.code.AbstractTemplate;
import spring.demo.trace.template.code.SubClassLogic1;
import spring.demo.trace.template.code.SubClassLogic2;

@Slf4j
public class TemplateMethodTest {

    @Test
    void tempateMethodV0(){
        logic1();
        logic2();
    }

    @Test
    void templateMethodV1(){
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }

    @Test
    void templateMethodV2(){
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비지니스 로직1 실행");
            }
        };
        log.info("클래스 이름1={}",template1.getClass().toString());
        template1.execute();

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비니지스 로직2 실행");
            }
        };
        log.info("클래스 이름2={}",template2.getClass().toString());
        template2.execute();
    }

    private void logic1(){
        long startTime =  System.currentTimeMillis();
        //비지니스 로직 실행
        log.info("비지니스 로직 실행");
        //비지니스 로직 종료
        long endTime  = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}",resultTime);
    }

    private void logic2(){
        long startTime = System.currentTimeMillis();
        //비지니스 로직 실행
        log.info("비지니스 로직 실행2");
        //비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}",resultTime);
    }
}
