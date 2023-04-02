package hell.demo.converter;

import org.springframework.format.Formatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class MyNumberFormatter implements Formatter<Number> {
    @Override
    public Number parse(String text, Locale locale) throws ParseException {
        NumberFormat format = NumberFormat.getInstance(locale);
        return format.parse(text);
    }
    @Override
    public String print(Number number  ,Locale locale){
        return NumberFormat.getInstance(locale).format(number);
    }
}
