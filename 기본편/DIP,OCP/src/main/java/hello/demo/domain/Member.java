package hello.demo.domain;

public class Member {

    private Long id;
    private boolean VIP=false;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setVip(){
        this.VIP=true;
    }
    public boolean isVip(){
        return VIP;
    }

}
