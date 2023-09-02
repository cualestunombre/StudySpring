package spring.demo.trace.logtrace;

import lombok.extern.slf4j.Slf4j;
import spring.demo.trace.TraceId;
import spring.demo.trace.TraceStatus;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace{
    private final Object fileLock = new Object(); // 동기화를 위한 락 객체
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<--";
    private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<TraceId>();
    private ThreadLocal<List<String>> logs = new ThreadLocal<>();
    @Override
    public TraceStatus begin(String message){
        syncTraceId();
        makeLogs();
        TraceId traceId = traceIdHolder.get();
        Long startTimeMs = System.currentTimeMillis();
        logs.get().add("["+traceId.getId()+"]"+" "+addSpace(START_PREFIX, traceId.getLevel())+message);
        return new TraceStatus(traceId,startTimeMs,message);
    }

    @Override
    public void end(TraceStatus status){
        complete(status,null);
    }
    @Override
    public void exception(TraceStatus status, Exception e){
        complete(status,e);
    }


    private void complete(TraceStatus status, Exception e){
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (e == null){
            logs.get().add("["+traceId.getId()+"]"+" "+addSpace(COMPLETE_PREFIX, traceId.getLevel())
                    +status.getMessage()+ "times="+resultTimeMs+"ms");
        }else{
            logs.get().add("["+traceId.getId()+"]"+" "+addSpace(EX_PREFIX, traceId.getLevel())
                    +status.getMessage()+ "times="+resultTimeMs+"ms"+" ex="+e.toString());
        }
        release();
    }

    private String addSpace(String prefix, int level){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level ; i++){
            sb.append((i == level - 1) ? "|" + prefix : "|  ");
        }
        return sb.toString();
    }

    private void release(){
        TraceId traceId = traceIdHolder.get();
        if(traceId.isFirstLevel()){
            traceIdHolder.remove();
            outputLogsToFile();

        }else{
            traceIdHolder.set(traceId.createPreviousId());
        }
    }
    private void makeLogs(){
        List<String> log = logs.get();
        if (log == null){
            logs.set(new ArrayList<String>());
        }
    }

    private void syncTraceId(){
        TraceId traceId = traceIdHolder.get();
        if (traceId == null){
            traceIdHolder.set(new TraceId());
        }else{
            traceIdHolder.set(traceId.createNextId());
        }
    }

    private void outputLogsToFile() {
        synchronized (fileLock) { // 동기화 블록 시작
            List<String> logEntries = logs.get();
            if (logEntries != null && !logEntries.isEmpty()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("log_output.txt", true))) {
                    for (String logEntry : logEntries) {
                        writer.write(logEntry);
                        writer.newLine();
                    }
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
