package study.section11.item78;

import java.util.concurrent.TimeUnit;

public class StopThread {
    private static boolean stopRequested;

    private static void requestStop() {
        stopRequested = true;
    }
    private static boolean stopRequested() {
        return stopRequested;
    }

    public static void main(String[] args)
        throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            //while (!stopRequiested()){
            while (!stopRequested()){
                i++;
            }
        });

        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        //stopRequiested = true;
        requestStop();
    }
}
