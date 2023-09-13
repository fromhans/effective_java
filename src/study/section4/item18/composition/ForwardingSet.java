package study.section4.item18.composition;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class ForwardingSet<E> implements Set<E> {

    private final Set<E> s;

    public ForwardingSet(Set<E> s) {
        this.s = s;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return s.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return s.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }
//
//    public int size() {
//        return s.size();
//    }
//
//    public boolean isEmpty() {
//        return s.isEmpty();
//    }
//
//    public boolean contains(Object o) {
//        return s.contains(o);
//    }
//
//    public Iterator<E> iterator() {
//        return s.iterator();
//    }
//
//    public Object[] toArray() {
//        return s.toArray();
//    }
//
//    public <T> T[] toArray(T[] a) {
//        return null;
//    }
//
//    public boolean add(E e) {
//        return false;
//    }
//
//    public boolean remove(Object o) {
//        return false;
//    }
//
//    public boolean containsAll(Collection<?> c) {
//        return false;
//    }
//
//    public boolean addAll(Collection<? extends E> c) {
//        return false;
//    }
//
//    public boolean retainAll(Collection<?> c) {
//        return false;
//    }
//
//    public boolean removeAll(Collection<?> c) {
//        return false;
//    }
//
//    public void clear() {
//
//    }
}
