package spring.demo.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring.demo.trace.strategy.code.strategy.ContextV1;
import spring.demo.trace.strategy.code.strategy.Strategy;
import spring.demo.trace.strategy.code.strategy.StrategyLogic1;
import spring.demo.trace.strategy.code.strategy.StrategyLogic2;

@Slf4j
public class ContextV1Test {
    @Test
    void strategyV0(){
        logic1();
        logic2();
    }

    @Test
    void strategyV1(){
        Strategy strategyLogic1 = new StrategyLogic1();
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();

        Strategy strategyLogic2 = new StrategyLogic2();
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();
    }
    //아예 Strategy를 lambda화 할 수도 있다
    @Test
    void strategyLambda(){
        ContextV1 context1 = new ContextV1(()->{log.info("비지니스1 실행");});
        ContextV1 context2 = new ContextV1(()->{log.info("비지니스2 실행");});
        context1.execute();
        context2.execute();
    }

    private void logic1(){
        long startTime = System.currentTimeMillis();
        //비지니스 로직 실행
        log.info("비지니스 로직1 실행");
        //비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}",resultTime);
    }

    private void logic2(){
        long startTime = System.currentTimeMillis();
        //비지니스 로직 실행
        log.info("비지니스 로직2 실행");
        //비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}",resultTime);
    }
}
