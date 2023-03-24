package hello.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@Controller
public class ExceptionTest {
    public static final String ERROR_EXCEPTION = "javax.servlet.error.exception";
    public static final String ERROR_EXCEPTION_TYPE = "javax.servlet.error.exception_type";
    public static final String ERROR_MESSAGE = "javax.servlet.error.message";
    public static final String ERROR_REQUEST_URI = "javax.servlet.error.request_uri";
    public static final String ERROR_SERVLET_NAME = "javax.servlet.error.servlet_name";
    public static final String ERROR_STATUS_CODE = "javax.servlet.error.status_code";



    @RequestMapping("/runtime")
    public void runtime(){
        throw new RuntimeException();
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
