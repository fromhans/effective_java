package study.section11.item81;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {
        int permits = 2; // 동시에 허용할 스레드 수
        Semaphore semaphore = new Semaphore(permits);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire(); // 리소스를 얻으려고 시도
                    System.out.println("리소스 사용 중...");
                    Thread.sleep(1000); // 가정: 어떤 작업 수행
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(); // 리소스 반환
                    System.out.println("리소스 반환");
                }
            }).start();
        }
    }
}
