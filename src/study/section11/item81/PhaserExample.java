package study.section11.item81;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Phaser;

public class PhaserExample {

    static Runnable run(Phaser ph) {
        return () -> {
            ph.register();
            try {
                Thread.sleep(1000L);
                System.out.println(Thread.currentThread().getName() + " arrived");
                ph.arriveAndAwaitAdvance(); // 페이즈에 참여하는 다른 모든 쓰레드의 종료지점 도착 대기
                //ph.arrive();
                System.out.println(Thread.currentThread().getName() + " after arrive()");
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " sleep end");
            ph.arriveAndDeregister();
        };
    }

    public static void main(String[] args) throws InterruptedException {

        Phaser phaser = new Phaser(0);

        for (int i = 0; i < 5; i++) { // 스레드 생성
            new Thread(run(phaser)).start();
        }

        System.out.println(Thread.currentThread().getName() + " arrived");
        phaser.arriveAndAwaitAdvance();

        System.out.println("main await end");
    }
}
    /**
     * public class Phaser {
     * 	// ...
     * 	// 동기화에 참여할 수 있는 스레드의 수를 늘린다.
     *     public int register()
     *
         *    // 등록된 스레드가 현재 단계를 완료했음을 알리는 데 사용한다.
         *    // 모든 등록된 스레드가 arrive()를 호출하면 Phaser는 다음 단계로 진행한다.
         *    // 마치 CyclicBarrier의 await()과 비슷하다고 생각하면 된다.
         *public int arrive(){  }
    *
    *    // 현재 스레드가 해당 단계를 완료했음을 알리고,
    *    // Phaser에서 스레드를 등록 해제한다. 즉, 스레드가 이후의 단계에
    *    // 참여하지 않게 된다. 이 메서드를 호출하면 Phaser에서 관리하는
    *    // 스레드의 수가 하나 감소하게 된다.
    *public int arriveAndDeregister(){ }
    *
    *    // 이 메서드는 현재 스레드가 해당 단계를 완료했음을 알리고,
    *    // 다음 단계로 진행되기 전에 모든 등록된 스레드가 해당 단계를 완료할 때까지
    *    // 기다린다. 즉, 스레드는 다음 단계로 진행하기 전에 다른 스레드들이 현재
    *    // 단계를 완료할 때까지 블록된다.
    *public int arriveAndAwaitAdvance(){}
    *}
     */
