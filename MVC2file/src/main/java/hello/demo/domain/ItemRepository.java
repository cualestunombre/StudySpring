package hello.demo.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemRepository {
    private final Map<Long,Item> store = new HashMap<>();
    private long sequence=0L;

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(),item);
        return item;
    }
    public Item findbyId(Long id){
        return store.get(id);
    }
}
