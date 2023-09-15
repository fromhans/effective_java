package study.section11.item83;

import java.util.ArrayList;
import java.util.List;
import study.section11.item81.SemaphoreExample;

public class LazyInitialization {

    // 일반적 초기화
    private final List normalInit = new ArrayList();

    // 지연 초기화
    private List lazyInit;

    public synchronized List getLazyInit() {
        if (lazyInit == null) {
            lazyInit = new ArrayList();
        }

        return lazyInit;
    }

    // 정적 필드 지연 초기화 - 홀더 클래스
    private static class ListHolder {
        static final List lazyInitWithHolder = new ArrayList();
    }

    public static List getLazyInitWithHolder(){
        return ListHolder.lazyInitWithHolder;
    }

    /// 불필요한 정적 필드 지연 초기화
    private static List lazyInitWithHolderV2;
    public synchronized List getLazyInitV2() {
        if (lazyInit == null) {
            lazyInit = new ArrayList();
        }

        return lazyInit;
    }

    //인스턴스 필드 지연 초기화용 이중 검사 관용구
    private volatile List lazyInitWithDoubleCheck;

    public List getLazyInitWithDoubleCheck() {
        List result = lazyInitWithDoubleCheck;
        if (result != null){
            return result;
        }

        synchronized(this) {
            if(lazyInitWithDoubleCheck == null){
                lazyInitWithDoubleCheck = new ArrayList();
            }
            return lazyInitWithDoubleCheck;
        }
    }

    // 단일 검사 관용구
    private volatile List lazyInitWithSingleCheck;

    public List getLazyInitWithSingleCheck() {
        // result는 volatile 변수를 딱 한번만 메모리에서 읽어오도록 보장
        List result = lazyInitWithSingleCheck;
        if (result != null){
            return result;
        }

        // 다른 쓰레드에서 이미 초기화를 했을 경우를 대비해, 새로 메모리에서 로드
        if(lazyInitWithSingleCheck == null){
            lazyInitWithSingleCheck = new ArrayList();
        }
        return lazyInitWithSingleCheck;
    }

    public static void main(String[] args) {

    }

}
