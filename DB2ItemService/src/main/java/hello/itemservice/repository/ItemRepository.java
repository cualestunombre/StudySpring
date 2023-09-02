package hello.itemservice.repository;

import hello.itemservice.domain.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository { //인터페이스 : 구현과 분리해야 한다.

    Item save(Item item);

    void update(Long itemId, ItemUpdateDto updateParam);
    void delete(Long itemId);

    Optional<Item> findById(Long id);

    List<Item> findAll(ItemSearchCond cond);

}
