package study.section5.item28;

public class OuterClass {

    private final InnerClass innerClass = new InnerClass();

    private static class InnerClass{

        private void callInnerPrivateMethod(){
            System.out.println("inner");
        };
    }

    void callOuter(){
        Class clazzType = Integer.class;
        String a = "asdf";

        innerClass.callInnerPrivateMethod();
    }
}
