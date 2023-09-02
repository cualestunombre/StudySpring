package hello.small.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Long price;

    @Enumerated(EnumType.STRING)
    private  Currency currency;


}
