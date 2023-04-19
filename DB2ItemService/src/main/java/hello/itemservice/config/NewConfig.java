package hello.itemservice.config;

import hello.itemservice.repository.ItemQueryRepositoryV2;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemRepositoryV2;
import hello.itemservice.repository.jpa.JpaItemRepositoryV3;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Configuration
public class NewConfig {
    private final EntityManager em;
    private final ItemRepositoryV2 itemRepositoryV2; //spring이 알아서 주입
    @Bean
    public ItemService itemService(){

        return new ItemServiceV2(itemRepositoryV2,itemQueryRepositoryV2());
    }
    @Bean
    public ItemQueryRepositoryV2 itemQueryRepositoryV2(){
        return new ItemQueryRepositoryV2(em);
    }
    @Bean
    public ItemRepository itemRepository(){
        return new JpaItemRepositoryV3(em);
    }

}
