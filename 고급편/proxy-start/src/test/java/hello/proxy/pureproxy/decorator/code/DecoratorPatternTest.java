package hello.proxy.pureproxy.decorator.code;

import org.junit.jupiter.api.Test;

public class DecoratorPatternTest {
    @Test
    void decorator1(){
        Component realComponent = new RealComponent();
        Decorator messageDecorator = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
        client.execute();
    }
    @Test
    void decorator2(){
        Component realComponent = new RealComponent();
        Decorator messageDecorator = new MessageDecorator(realComponent);
        Decorator timeDecorator = new TimeDecorator(messageDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);
        client.execute();
    }
}
