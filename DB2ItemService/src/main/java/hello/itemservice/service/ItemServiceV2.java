package hello.itemservice.service;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemQueryRepositoryV2;
import hello.itemservice.repository.ItemRepositoryV2;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class ItemServiceV2 implements ItemService{
    private final ItemRepositoryV2 itemRepositoryV2;
    private final ItemQueryRepositoryV2 itemQueryRepositoryV2;

    @Override
    public Item save(Item item){
        return itemRepositoryV2.save(item);
    }
    @Override
    public void update(Long itemId, ItemUpdateDto updateParam){
        Item item = findById(itemId).orElseThrow();
        item.setItemName(updateParam.getItemName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());

    }
    @Override
    public void delete(Long itemId){
        Item item = findById(itemId).orElse(null);
        itemRepositoryV2.delete(item);
    }
    @Override
    public Optional<Item> findById(Long id){
        return itemRepositoryV2.findById(id);
    }
    @Override
    public List<Item> findItems(ItemSearchCond cond){
        return itemQueryRepositoryV2.findAll(cond);
    }
}
