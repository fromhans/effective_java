package study.concurrency;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {

    public static void main(String[] args) {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("before interrupt flag configured");
            //Thread.currentThread().interrupt(); // 인터럽트가 설정됐으나, 해당 쓰레드의 동작이 중단되지는 않음.
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("interrupt flag configured");
            return 42;
        });

        try {
            System.out.println("future state: " + future.state());
            int result = future.exceptionally(throwable -> {
                System.out.println("in the exceptionally");
                return 1000;
            }).get(); //get()시 쓰레드의 인터럽트 플래그가 true이면 인터럽트 exception 발생
            System.out.println("future state: " + future.state());
            System.out.println("Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Exception: " + e);
        }
    }
}
