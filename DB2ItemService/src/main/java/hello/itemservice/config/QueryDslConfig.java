package hello.itemservice.config;

import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.JdbcTemplateItemRepositoryV1;
import hello.itemservice.repository.jpa.JpaItemRepositoryV3;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class QueryDslConfig {

    @Autowired
    public EntityManager em;
    @Bean
    public ItemService itemService(){
        return new ItemServiceV1(itemRepository());
    }
    @Bean
    public ItemRepository itemRepository(){
        return new JpaItemRepositoryV3(em);
    }
}