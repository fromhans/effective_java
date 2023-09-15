package study.section11.item84;

public class SlowCountDownLatch {
    private int count;

    public SlowCountDownLatch(int count){
        if (count<0) throw new IllegalStateException(count + " <0");

        this.count = count;
    }

    public void await() {
        //busy waiting 구간
        while(true){
            synchronized (this){
                if(count == 0) return;
            }
        }
    }

    public synchronized void countDown() {
        if (count !=0) count--;
    }
}
