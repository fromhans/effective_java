package study.section4.item19;

public class Super {
    public Super() {
        System.out.println("start in the super constructor");
        thisisPrivate();
        System.out.println("end in the super constructor");
    }

    private void thisisPrivate(){
        System.out.println("In The Super class Private method");
    }

    protected void overrideMe() {
        System.out.println("start in the super overridable method");
        //thisisPrivate();
        System.out.println("end in the super overridable method");
    }
}
