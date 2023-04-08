package hello.jdbc.repository.ex;

public class MyDuplicateKeyException extends MyDbException{
    public MyDuplicateKeyException(){

    }
    public MyDuplicateKeyException(String message){
        super(message);
    }
    public MyDuplicateKeyException(String message, Throwable cause){
        super(cause,message);
    }
    public MyDuplicateKeyException(Throwable cause){
        super(cause);
    }
}
