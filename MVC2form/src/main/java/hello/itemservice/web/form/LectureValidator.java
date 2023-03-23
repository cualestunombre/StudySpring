package hello.itemservice.web.form;
import hello.itemservice.domain.item.Lecture;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class LectureValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz){
        return Lecture.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors){
        Lecture lecture = (Lecture) target;
        if(!StringUtils.hasText(lecture.getName()) || lecture.getName().length()>10){
            errors.rejectValue("name","required",new Object[]{10},null);
        }
        if(lecture.getPrice()==null || lecture.getPrice()>200000){
            errors.rejectValue("price","max",new Object[]{200000},null);
        }
        if(lecture.getSubject()==null){
            errors.rejectValue("subject","choose");
        }
        if(lecture.getTarget().size()<2){
            errors.rejectValue("target","min",new Object[]{2},null);
        }

    }
}
