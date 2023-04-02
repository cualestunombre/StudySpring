package hello.demo;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest
@Component
public class SpringConfigTest{

	@Autowired public ApplicationContext context;


	@Test
	public void printBeanList() {
		String[] beanNames = context.getBeanDefinitionNames();
		System.out.println("빈의 개수는 "+ StringUtils.toString(beanNames.length)+"개 입니다");

	}
}
