package ru.academits.vasilev.arraylist;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        data = (T[]) new Object[capacity];
    }

    public void ensureCapacity(int capacity) {
        data = Arrays.copyOf(data, capacity);
    }

    public void trimToSize() {
        data = Arrays.copyOf(data, size);
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object element) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        int cursor;
        int lastRet = -1;
        //int expectedModCount = modCount;

        MyIterator() {
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            //checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            //Object[] elementData = ArrayList.this.elementData;
            //if (i >= elementData.length)
            throw new ConcurrentModificationException();
            //cursor = i + 1;
            //return (T) elementData[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            //checkForComodification();

            try {
                //ArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                //expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    //Последные методны реализовывать не нужно
    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
