package study.section11.item81;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PhaserExample2 {
    public static long time(Executor executor, int concurrency, Runnable action) throws InterruptedException {
        CountDownLatch ready = new CountDownLatch(concurrency);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(concurrency);

        for (int i=0; i<concurrency; i++){
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " - ready count down");
                ready.countDown(); //메인쓰레드에게 준비 완료 보고
                try {
                    System.out.println(Thread.currentThread().getName() + " - waiting for start of main thread");
                    start.await(); // 메인쓰레드가 start.countDown()해주길 대기
                    action.run();
                } catch (InterruptedException | RuntimeException e) {
                    System.out.println(Thread.currentThread().getName() + " - exception catched");
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown();
                    System.out.println(Thread.currentThread().getName() + " - done count down");
                }
            });
        }

        ready.await();
        long startNanos = System.nanoTime();
        start.countDown();
        done.await();
        return System.nanoTime() - startNanos;
    }

    public static void main(String[] args) {
        Executor executor = Executors.newFixedThreadPool(3);
        try {
            time(executor, 3, () -> {
                System.out.println("test Runnable");
                throw new RuntimeException("test");
            });
        } catch (InterruptedException | RuntimeException e){
            System.out.println(Thread.currentThread().getName() + " - exception catched in main");
        }
    }
}
