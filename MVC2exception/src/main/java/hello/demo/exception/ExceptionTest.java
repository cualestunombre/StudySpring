package hello.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller //에러 전용 컨트롤러를 직접 사용할 때 필요한 컨트롤러
public class ExceptionTest {
    public static final String ERROR_EXCEPTION = "javax.servlet.error.exception";
    public static final String ERROR_EXCEPTION_TYPE = "javax.servlet.error.exception_type";
    public static final String ERROR_MESSAGE = "javax.servlet.error.message";
    public static final String ERROR_REQUEST_URI = "javax.servlet.error.request_uri";
    public static final String ERROR_SERVLET_NAME = "javax.servlet.error.servlet_name";
    public static final String ERROR_STATUS_CODE = "javax.servlet.error.status_code";

    @RequestMapping(value="/error-page/500",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Object>> error500API(HttpServletRequest req, HttpServletResponse res){
        log.info("API errorPAge 500");

        Map<String,Object> result = new HashMap<>();
        Exception ex = (Exception) req.getAttribute(ERROR_EXCEPTION);
        result.put("status",req.getAttribute(ERROR_STATUS_CODE));
        result.put("message",ex.getMessage());

        Integer statusCode = (Integer) req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        return new ResponseEntity<>(result, HttpStatus.valueOf(statusCode));



    }

    @RequestMapping("/runtime")
    public void runtime(){
        throw new RuntimeException("런타임 에러 발생");
    }
    @RequestMapping("/error-page/404")
    public String error404(HttpServletResponse res, HttpServletRequest req) throws IOException {
        printErrorInfo(req);
        return "error-page/404";
    }
    @RequestMapping("/error-page/500")
    public String error500(HttpServletResponse res,HttpServletRequest req) throws IOException{

        printErrorInfo(req);
        return "error-page/500";
    }
    public void printErrorInfo(HttpServletRequest req){
        log.info("error_exception={}",req.getAttribute(ERROR_EXCEPTION));
        log.info("error_exception_type={}",req.getAttribute(ERROR_EXCEPTION_TYPE));
        log.info("error_message={}",req.getAttribute(ERROR_MESSAGE));
        log.info("error_request_uri={}",req.getAttribute(ERROR_REQUEST_URI));
        log.info("error_servlet_name={}",req.getAttribute(ERROR_SERVLET_NAME));
        log.info("error_status_code={}",req.getAttribute(ERROR_STATUS_CODE));
        log.info("dispatchType={}",req.getDispatcherType());
    }
}
