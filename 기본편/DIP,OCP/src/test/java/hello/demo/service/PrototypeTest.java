package hello.demo.service;


import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/*
프로토타입과 싱글톤을 함께 쓸 때 어떻게 할 것인가??
*/

public class PrototypeTest {

    @Test
    public void Prototype(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(Client.class,Server.class);
        Client client = ac.getBean(Client.class);
        client.logic();
        Server server1 = client.getServer();
        client.logic();
        Server server2 = client.getServer();
        assertThat(server1).isNotEqualTo(server2);

    }


    @Component
    @Scope("singleton")
    static class Client{
        @Autowired
        private ObjectProvider<Server> objectProvider;
        private Server server;

        public int logic(){
            Server server = objectProvider.getObject();
            int count = server.getCount();
            this.server=server;
            return count;
        }
        public Server getServer(){
            return this.server;
        }


    }
    @Scope("prototype")
    static class Server{
        private int count=0;
        public int getCount(){
            return ++this.count;
        }
    }
}
