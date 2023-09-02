package hello.logic.component;

import hello.logic.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Getter
@Setter
public class AuthenticationServerProxy {
    @Autowired
    private RestTemplate template;

    @Value("${auth.server.base.url}")
    private String baseUrl;

    public void sendAuth(String name,String password){
        String url = baseUrl + "/member/auth";

        Member member = new Member();
        member.setName(name);
        member.setPassword(password);

        var request = new HttpEntity<>(member);

        template.postForEntity(url,request,Void.class);

    }

    public boolean sendOTP(String name,String code){
        String url = baseUrl + "/otp/check";

        var body = new Member();
        body.setName(name);
        body.setCode(code);

        var request = new HttpEntity<>(body);

        var reponse = template.postForEntity(url,request,Void.class);

        return reponse.getStatusCode().equals(HttpStatus.OK);

    }

}
