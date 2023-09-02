package hello.Oauth2Resource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticlesController {
    @GetMapping("/articles")
    public String[] getArticles(){
        return new String[] {"a","b","c"};
    }
}
