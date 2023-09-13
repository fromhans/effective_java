package study.section11.item80;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        Runnable runnable = () -> System.out.println("executing runnable");

        //단순 실행
        exec.execute(runnable);

        // 결과 받기
        Object o = exec.submit(runnable).get();

        // invokeAny
        System.out.println("--- InvokeAny");
        List<Callable<String>> callables = new ArrayList<>();
        callables.add(()-> {
            System.out.println("call1");
            return "call1";
        });
        callables.add(()-> {
            System.out.println("call2");
            return "call2";
        });
        String result = exec.invokeAny(callables);

        // invokeAll
        System.out.println("--- InvokeAll");
        List<Callable<String>> tasks = new ArrayList<>();
        tasks.add(() -> "Task 1 result");
        tasks.add(() -> "Task 2 result");
        tasks.add(() -> "Task 3 result");

        List<Future<String>> results = exec.invokeAll(tasks);

        for (Future<String> taskResult : results) {
            System.out.println("Result: " + taskResult.get());
        }

        // awaitTermination
        exec.awaitTermination(10, TimeUnit.SECONDS); //10초간 쓰레드 종료 대기

        // 완료된 태스크 차례로 결과 받기
        System.out.println("--- ExecutorCompletionService");
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(executorService);

        completionService.submit(() -> {
            Thread.sleep(2000); // 예시로 2초 동안 대기
            return "Task 1 result";
        });

        completionService.submit(() -> {
            Thread.sleep(1000); // 예시로 1초 동안 대기
            return "Task 2 result";
        });

        completionService.submit(() -> {
            Thread.sleep(3000); // 예시로 3초 동안 대기
            return "Task 3 result";
        });

        // 작업이 완료될 때까지 결과를 가져옴
        for (int i = 0; i < 3; i++) {
            Future<String> taskResult = completionService.take(); // 완료된 작업 가져오기 (블로킹)
            System.out.println("Result: " + taskResult.get()); // 결과 출력
        }

        executorService.shutdown();

        exec.shutdown();

    }

}
