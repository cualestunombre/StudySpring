package hello.itemservice.domain.item;

public enum Subject {
    MATH("수학"),ENGLISH("영어"),SCIENCE("과학"),SOCIAL_STUDIES("사회");
    private String description;
    Subject(String description){
        this.description=description;
    }
    public String getDescription(){
        return this.description;
    }
}
