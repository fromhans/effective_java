package study.section5.item28;

import java.util.ArrayList;
import java.util.List;

public class InstanceOf {

    public static void main(String[] args) {
        String a = "not a string list";
        List<String> answer = returnList(a);
//        if (returnList(a) instanceof List<String>){
//            System.out.println();
//        }
    }

    static List returnList(String str){
        if (str.equals("List<String>")){
            return new ArrayList<String>();
        } else {
            List<Integer> asdf = new ArrayList<>();
            asdf.add(5);

            return asdf;
        }
    }

}
