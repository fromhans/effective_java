package study.section4.item20;

public interface EqualsInterface {

    default Object clone(){
        System.out.println("디폴트 메소드 클론 호출됨");
        return new Object();
    }

//    String test();
}
