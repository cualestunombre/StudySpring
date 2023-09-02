package hello.springtx.propagation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Log {
    @Id
    @GeneratedValue
    private Long id;
    private String message;

    public Log(){// JPA는 기본 생성자 필수 

    }

    public Log(String message){
        this.message = message;
    }
}
