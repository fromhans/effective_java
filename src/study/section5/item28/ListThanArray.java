package study.section5.item28;

import java.util.ArrayList;
import java.util.List;

public class ListThanArray {

    public static void main(String[] args) {

        List<String> strings = new ArrayList<String>();
        List<Integer> integers = new ArrayList<Integer>();

        System.out.println(strings.getClass() == integers.getClass());
        System.out.println(strings instanceof List);
        System.out.println(integers instanceof List);
        System.out.println(strings instanceof  List<?>);
        System.out.println(integers instanceof List<?>);
        System.out.println(strings instanceof List<String>);
        System.out.println(integers instanceof List<Integer>);
    }
}
