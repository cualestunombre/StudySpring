package hello.itemservice.repository;

import hello.itemservice.domain.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Transactional
public class JpaItemRepositoryV1 implements ItemRepository{
    private final EntityManager em;
    //내부에 dataSource를 갖고 있다, 역시 스프링이 알아서 주입을 해준다.

    public JpaItemRepositoryV1(EntityManager em){
        this.em = em;
    }
    @Override
    public Item save(Item item){
        em.persist(item);
        //id 마저 넣어준다
        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam){
        Item findItem = em.find(Item.class,itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
        //이게 된다니?!
    }
    @Override
    public Optional<Item> findById(Long id){
        Item item = em.find(Item.class,id);
        return Optional.ofNullable(item);
    }
    @Override
    public void delete(Long itemId){
        Item item = em.find(Item.class,itemId);
        em.remove(item);
    }
    @Override
    public List<Item> findAll(ItemSearchCond cond){
        String jpql = "select i from Item i";
        Integer maxPrice = cond.getMaxPrice();
        String itemName = cond.getItemName();

        if(StringUtils.hasText(itemName) || maxPrice !=null){
            jpql += " where";
        }
        boolean andFlag = false;
        if(StringUtils.hasText(itemName)){
            jpql+= " i.itemName like concat('%',:itemName,'%')";
            andFlag=true;
        }
        if(maxPrice!=null){
            if(andFlag){
                jpql+=" and";
            }
            jpql+=" i.price <= :maxPrice";
        }
        log.info("jpql={}",jpql);
        TypedQuery<Item> query = em.createQuery(jpql, Item.class);
        if(StringUtils.hasText(itemName)){
            query.setParameter("itemName",itemName);
        }
        if(maxPrice != null){
            query.setParameter("maxPrice",maxPrice);
        }
        return query.getResultList();

    }
}
