package hello.demo.controller;

public class MemberForm {
    private String name; // input tag의 name을 보고 자동으로 name을 넣어 준다
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
