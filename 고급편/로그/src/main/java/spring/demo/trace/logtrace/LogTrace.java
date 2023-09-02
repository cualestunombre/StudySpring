package spring.demo.trace.logtrace;

import spring.demo.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status,Exception e);
}
