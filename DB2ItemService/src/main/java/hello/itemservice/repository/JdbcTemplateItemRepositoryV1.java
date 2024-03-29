package hello.itemservice.repository;

import hello.itemservice.domain.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class JdbcTemplateItemRepositoryV1 implements ItemRepository{
    private final JdbcTemplate template;
    //기본적으로 dataSource는 설정이 없으면 스프링이 자동으로 주입한다
    public JdbcTemplateItemRepositoryV1(DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }
    @Override
    public Item save(Item item){
        String sql = "insert into item (item_name, price, quantity) values (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(con -> { // update는 주로 데이터를 변경할 때 사용한다. return 값으로, 영향받은 로우 수를 리턴한다
            //update메서드는 INSERT, UPDATE, DELETE SQL에 사용한다
            PreparedStatement ps = con.prepareStatement(sql,new String[]{"id"});
            ps.setString(1,item.getItemName());
            ps.setInt(2,item.getPrice());
            ps.setInt(3,item.getQuantity());
            return ps;
        },keyHolder);
        long key = keyHolder.getKey().longValue();
        item.setId(key);
        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam){
        String sql = "update item set item_name=?, price=?, quantity=? where id=?";
        template.update(sql,updateParam.getItemName(),updateParam.getPrice(),updateParam.getQuantity(),itemId);
    }
    @Override
    public void delete(Long itemId){
        String sql = "delete from item where id=?";
        template.update(sql,itemId);
        return;
    }
    @Override
    public Optional<Item> findById(Long id){
            String sql = "select id, item_name, price, quantity from item where id=?";
            try{
                Item item = template.queryForObject(sql,itemRowMapper(),id); //결과로우가 하나일 때 사용함
                //결과가 없거나 둘 이상이면 예외가 발생한다
                return Optional.of(item);
            }catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }
    @Override
    public List<Item> findAll(ItemSearchCond cond){
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();

        String sql = "select id, item_name, price, quantity from item ";

        if(StringUtils.hasText(itemName)|| maxPrice!=null){
            sql+="where ";
        }
        boolean andFlag=false;
        List<Object> param = new ArrayList<>();
        if(StringUtils.hasText(itemName)){
            sql+="item_name like concat('%',?,'%')";
            param.add(itemName);
            andFlag=true;
        }
        if(maxPrice!=null){
            if(andFlag){
                sql+= "and";
            }
            sql += " price <= ?";
            param.add(maxPrice);
        }
        log.info("sql={}",sql);
        return template.query(sql,itemRowMapper(),param.toArray()); //결과가 하나 이상일 때 사용한다


    }

    private RowMapper<Item> itemRowMapper(){
        return(rs,rowNum)->{
          Item item = new Item();
          item.setId(rs.getLong("id"));
          item.setItemName(rs.getString("item_name"));
          item.setPrice(rs.getInt("price"));
          item.setQuantity(rs.getInt("quantity"));
          return item;
        };
    }

    /*
    원시값을 쿼리하고 싶으면
    template.query(sql,Integer.class,인수);를 하면 된다

     */

}
