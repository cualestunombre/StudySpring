package hello.demo.domain;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class Member {
   private Long id;
   private String name;
   private int age;
   public Member(){}
    public Member(String name, int age){
       this.name=name;
       this.age=age;
    }


}
