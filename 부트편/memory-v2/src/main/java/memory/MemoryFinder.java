package memory;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemoryFinder {
    public Memory get(){
        Long max = Runtime.getRuntime().maxMemory();
        Long total = Runtime.getRuntime().totalMemory();
        Long free = Runtime.getRuntime().freeMemory();
        Long used = total - free;
        return new Memory(used, max);
    }
    @PostConstruct
    public void init(){
        log.info("init memoryFinder");
    }
}
