package hello.demo.service;

import javax.annotation.PostConstruct;

public class NetworkService {
    private String url;
    public void setUrl(String url){
        this.url = url;
    }
    @PostConstruct
    public void Connect(){
        System.out.println(this.url);
    }


}
