package study.section11.item81;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 3;
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                // 스레드의 작업 수행
                System.out.println("작업을 수행 중...");
                latch.countDown(); // 작업이 완료될 때마다 카운트 다운
            }).start();
        }

        latch.await(); // 모든 작업이 완료될 때까지 대기
        System.out.println("모든 작업 완료!");
    }
}
