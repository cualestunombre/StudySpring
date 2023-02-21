package hello.demo.service;

import hello.demo.domain.Language;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("java")
public class Java implements LanguageSelection {
    public Language code = Language.java;

    @Override
    public Language getCode() {
        return code;
    }
}
