package hello.demo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;
public class SpringCofigTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String [] beans = ac.getBeanDefinitionNames();
        for(String bean : beans){
            Object abean = ac.getBean(bean);
            System.out.println(bean+abean);

        }
    }
}
