package hello.itemservice.domain.item;

public enum Target {
    H1("고1"),H2("고2"),H3("고3");
    final private String description;
    Target(String description){
        this.description=description;
    }
    public String getDescription(){
        return this.description;
    }
}
