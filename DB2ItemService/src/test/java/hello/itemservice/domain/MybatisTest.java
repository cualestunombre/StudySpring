package hello.itemservice.domain;

import hello.itemservice.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisTest {
    @Autowired
    ItemRepository repository;


    @Test
    void myBatisId(){
        Item item = new Item();
        repository.save(item);
        System.out.println(item.getId());
        Item item2 = new Item();
        repository.save(item);
        System.out.println(item2.getId());
    }

}
