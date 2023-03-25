package hello.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
public class APIExceptionController {
    @GetMapping("/api/members/{id}")
    public MemberDto getMember(@PathVariable String id, HttpServletResponse res) throws IOException {
        if(id.equals("ex")) {
            throw new RuntimeException("ex는 치지마세요 서버 고장나요");
        }
        if(id.equals("bad")){
            throw new IllegalArgumentException("잘못된 요청입니다");
        }
        if(id.equals("user-ex")){
            throw new UserException("사용자 오류");
        }
            return new MemberDto(id,"hello"+id);
    }
    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String memberId;
        private String name;
    }

}
