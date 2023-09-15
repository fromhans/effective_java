package study.section11.item78;

import java.util.concurrent.TimeUnit;

public class VolatileNumber {

    private static volatile int nextSerialNumber = 0;


    public static  int generateSerialNumber() {
        return nextSerialNumber++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            int i = 0;
            while (i < 10000) {
                i++;
                generateSerialNumber();
            }
        });

        Thread t2 = new Thread(() -> {
            int i = 0;
            while (i < 10000) {
                i++;
                generateSerialNumber();
            }
        });

        t1.start();
        t2.start();

        while (t2.isAlive() || t1.isAlive()) {
            // t2 쓰레드가 종료될 때까지 대기
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println(nextSerialNumber);
    }
}
