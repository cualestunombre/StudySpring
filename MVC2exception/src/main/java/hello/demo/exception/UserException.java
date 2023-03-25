package hello.demo.exception;

public class UserException extends RuntimeException{
    public UserException(){
        super();
    }
    public UserException(String message){
        super(message);
    }
    public UserException(String message, Throwable cause){
        super(message, cause);
    }
    public UserException(Throwable cause){
        super(cause);
    }
    protected UserException(String message,Throwable cause, boolean enableSuppression, boolean writableStackTree){
        super(message,cause,enableSuppression,writableStackTree);
    }
}
