package hello.demo.exhandler;

import hello.demo.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApiExceptionV2Controller {
    @Data
    @AllArgsConstructor
    static class MemberDto{
        private int memberId;
        private String name;
    }

    @PostMapping("/api2/members")
    public MemberDto getMember(@ModelAttribute MemberDto memberDto){
        int id = memberDto.getMemberId();
        String name = memberDto.getName();
        if(name.equals("ex")){
            throw new RuntimeException("잘못된 사용자");
        }
        if(name.equals("bad")){
            throw new IllegalArgumentException("잘못된 입력 값");
        }
        if(name.equals("user-ex")){
            throw new UserException("사용자 오류");
        }
        return memberDto;
    }

}
