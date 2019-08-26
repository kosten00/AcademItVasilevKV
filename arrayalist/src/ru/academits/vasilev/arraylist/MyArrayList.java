package ru.academits.vasilev.arraylist;

import java.util.Arrays;
import java.util.Collection;

public class MyArrayList<T> implements MyList<T> {
    private Object[] data;
    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    private void growCapacity() {
        data = Arrays.copyOf(data, data.length * 2);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("There are no elements with such index");
        }
    }

    public MyArrayList() {
        data = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        data = new Object[capacity];
    }

    public void ensureCapacity(int capacity) {
        data = Arrays.copyOf(data, capacity);
    }

    public void trimToSize() {

    }

    @Override
    public void add(int index, T element) {
        checkIndex(index);

        if (index == data.length) {
            growCapacity();
        }

        data[index] = element;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public int indexOf(Object object) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object object) {
        return 0;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T set(int index, T object) {
        return null;
    }
}
