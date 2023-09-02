package hello.proxy.pureproxy.decorator.code;

public abstract class Decorator implements Component{
    protected final Component component;
    Decorator(Component component){
        this.component = component;
    }
}
