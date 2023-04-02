package hell.demo.controllers;

import hell.demo.converter.MyNumberFormatter;
import hell.demo.domain.SockAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.text.ParseException;
import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/convert")
public class ConverterController {
    @GetMapping("")
    @ResponseBody
    public SockAddress IpToSTring(@RequestParam SockAddress sockAddress){
        log.info("ip : {}",sockAddress.getIp());
        log.info("port :{}",sockAddress.getPort());
        return sockAddress;
    }
    @GetMapping("/view")
    public String converterView(@RequestParam SockAddress sockAddress,Model model){
        model.addAttribute("ip",sockAddress);
        return "converterView";
    }
    @ResponseBody
    @GetMapping("/money")
    public String numberConvert(@RequestParam Number number, Locale locale) throws ParseException {
        System.out.println(locale);
        log.info("money :{}",number);
        MyNumberFormatter s = new MyNumberFormatter();
        return s.print(number,locale);
    }
}
