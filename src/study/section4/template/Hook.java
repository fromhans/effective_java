package study.section4.template;

public interface Hook {

    default String logic(){
        if(hook()){
            doSomething();
        };
        return "";
    }

    void doSomething();

    boolean hook();

}
