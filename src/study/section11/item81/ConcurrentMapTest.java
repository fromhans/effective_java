package study.section11.item81;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapTest {
    private static final ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, String> map2 = new ConcurrentHashMap<>();

    public static String intern(String s){
        String previousValue = map.putIfAbsent(s, s);

        return previousValue == null ? s : previousValue;
    }

    public static String internV2(String s){
        String result = map2.get(s);
        if (result == null) {
            result = map2.putIfAbsent(s, s);
            if (result == null)
                result = s;
        }
        return result;
    }

    public static void main(String[] args) {
        long startTime;
        long endTime;

        startTime = System.nanoTime();

        for(int i=0; i<10000; i++){
            intern(String.valueOf(i));
        }

        endTime = System.nanoTime();
        Duration executionDuration = Duration.ofNanos(endTime - startTime);
        System.out.println("코드 실행시간: " + executionDuration.toNanos() + " 나노초"); //6559954


        startTime = System.nanoTime();

        for(int i=0; i<10000; i++){
            internV2(String.valueOf(i));
        }

        endTime = System.nanoTime();
        executionDuration = Duration.ofNanos(endTime - startTime);
        System.out.println("코드 실행시간: " + executionDuration.toNanos() + " 나노초"); // 5221130
    }
}
