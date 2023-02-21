package hello.demo.service;
import hello.demo.domain.Language;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Python implements LanguageSelection{
    public Language code = Language.python;

    @Override
    public Language getCode() {
        return code;
    }
}
