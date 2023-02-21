package hello.demo.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
class ClientBean{
    @Autowired
    private ObjectProvider<PrototypeBean> provider;
    @Autowired
    private AnnotationConfigApplicationContext ac;
    void check(){
        System.out.println(provider);
        System.out.println(ac);
        System.out.println(ac.getBean(AnnotationConfigApplicationContext.class));
        System.out.println(ac.getBean(ObjectProvider.class));

    }
}
@Component
@Scope("prototype")
class PrototypeBean{

}