package hello.demo.exhandler;

import hello.demo.exception.APIExceptionController;
import hello.demo.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = {ApiExceptionV2Controller.class})
public class ExControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandle(IllegalArgumentException e){
        return new ErrorResult("BAD",e.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandle(UserException e){
        ErrorResult errorResult = new ErrorResult("USER-EX",e.getMessage());
        return new ResponseEntity<>(errorResult,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResult exHandle(Exception e){
        return new ErrorResult("EX","내부오류");
    }
}
