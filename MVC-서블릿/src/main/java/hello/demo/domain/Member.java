package hello.demo.domain;

public class Member {
    private int id;
    private Language language;
    public Member(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage() {
        if(this.language==Language.java) return "java";
        if(this.language==Language.python) return "python";
        return "none";

    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
