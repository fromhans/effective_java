package study.section4.item19;

public class Sub extends Super {

    private final String tempStr;

    public Sub(String s) {
        System.out.println("start in the sub constructor");
        this.tempStr = s;
        System.out.println("end in the sub constructor");
    }

    private void thisisPrivate() {
        System.out.println("in the subclass private method");
    }

    @Override
    protected void overrideMe() {
        System.out.println("start in the sub overridable method");
        System.out.println(tempStr);
        thisisPrivate();
        System.out.println("end in the sub overridable method");
    }
}