package hello.jdbc.repository.ex;

public class MyDbException extends RuntimeException{
    public MyDbException(){

    }
    public MyDbException(String message){
        super(message);
    }
    public MyDbException(Throwable cause,String message){
        super(message, cause);
    }
    public MyDbException(Throwable cause){
        super(cause);
    }
}
