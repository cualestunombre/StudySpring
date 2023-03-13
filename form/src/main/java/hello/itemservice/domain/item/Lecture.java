package hello.itemservice.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.*;
@Data
public class Lecture {
    private Long id;
    private String name;
    private Long price;
    private Subject subject;
    private List<Target> target;
}
