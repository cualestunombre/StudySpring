package memory;

public class Memory {
    private final long used;
    private final long max;

    public Memory(Long used, Long max){
        this.used = used;
        this.max = max;
    }

    public Long getUsed(){
        return used;
    }
    public Long getMax(){
        return max;
    }

    @Override
    public String toString(){
        return "Memory{" +
                "used=" + used + ", max=" + max +
                "}";
    }
}
