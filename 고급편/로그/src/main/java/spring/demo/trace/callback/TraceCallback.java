package spring.demo.trace.callback;

public interface TraceCallback<T> {
    T call();
}
