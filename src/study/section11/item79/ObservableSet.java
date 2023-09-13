package study.section11.item79;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import study.section4.item18.composition.ForwardingSet;

public class ObservableSet<E> extends ForwardingSet<E> {

    public ObservableSet(Set<E> s) {
        super(s);
    }

    //private final List<SetObserver<E>> observers = new ArrayList<>();
    private final List<SetObserver<E>> observers = new CopyOnWriteArrayList<>();

    public void addObserver(SetObserver<E> observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public boolean removeObserver(SetObserver<E> observer) {
        synchronized (observers) {
            return observers.remove(observer);
        }
    }

    private void notifyElementAdded(E element) {
        synchronized (observers) {
            for (SetObserver<E> observer : observers) {
                observer.added(this, element);
            }
        }

//        List<SetObserver<E>> snapshot = null;
//        synchronized (observers) {
//            snapshot = new ArrayList<>(observers);
//        }
//        for (SetObserver<E> observer : snapshot) {
//            observer.added(this, element);
//        }
    }

    @Override
    public boolean add(E element) {
        boolean added = super.add(element);
        if (added) {
            notifyElementAdded(element);
        }
        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E element : c) {
            result |= add(element);
        }
        return result;
    }

    public static void main(String[] args) {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

        // Normal case
        //set.addObserver((s, e) -> System.out.println(e));

        // Concurrent Modification
        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);
                if (element == 23) set.removeObserver(this);

            }
        });

        //ExecutorService
//        set.addObserver(new SetObserver<Integer>() {
//            @Override
//            public void added(ObservableSet<Integer> set, Integer element) {
//                System.out.println(element);
//
//                if (element == 23) {
//                    ExecutorService exec = Executors.newSingleThreadExecutor();
//                    try {
//                        exec.submit(() -> set.removeObserver(this)).get();
//                    } catch (ExecutionException | InterruptedException e) {
//                        throw new AssertionError(e);
//                    } finally {
//                        exec.shutdown();
//                    }
//                }
//            }
//        });

        for(
            int i = 0;
            i<100;i++)set.add(i);
        }
    }
