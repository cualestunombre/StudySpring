package hello.itemservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest
class ItemServiceApplicationTests {
	@Autowired
	MessageSource ms;
	@Test
	void helloMessage(){
		String result = ms.getMessage("hello",null,null);
		assertThat(result).isEqualTo("안녕");
	}


}
