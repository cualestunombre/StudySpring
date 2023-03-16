package hello.itemservice.validation;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import hello.itemservice.domain.item.Item;

import java.util.Set;

public class BeanValidationTest {

    @Test
    void beanValidation(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator(); //component에 validator가 등록이 돼있음.

        Item item = new Item();
        item.setPrice(0);
        item.setItemName(" ");
        item.setQuantity(10000);
        Set<ConstraintViolation<Item>> violations = validator.validate(item); //꺼내서 검증 함 + message를 담음(스프링이 생성)
        for (ConstraintViolation<Item> violation : violations){
            System.out.println("violation= "+ violation);
            System.out.println("violation.message= "+violation.getMessage());
        }
    }

}
