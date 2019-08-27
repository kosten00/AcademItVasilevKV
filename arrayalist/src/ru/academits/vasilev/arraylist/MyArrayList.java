package ru.academits.vasilev.arraylist;

import java.util.Arrays;

public class MyArrayList<T> implements MyList<T> {
    private Object[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    private void growCapacity() {
        data = Arrays.copyOf(data, data.length * 2);
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index " + index + " is out of the list size = " + size);
        }
    }

    private void checkSize() {
        if (size == data.length) {
            growCapacity();
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
        data = Arrays.copyOf(data, size);
    }

    public void addFirst(T element) {
        checkSize();

        System.arraycopy(data, 0, data, 1, size);

        data[0] = element;
        size++;
    }

    public void addLast(T element) {
        checkSize();

        data[size] = element;
        size++;
    }

    @Override
    public void add(int index, T element) {
        if (index == 0) {
            addFirst(element);
        } else {
            checkIndex(index);
            checkSize();

            System.arraycopy(data, index, data, index + 1, size - index);

            data[index] = element;
            size++;
        }
    }

    @Override
    public boolean addAll(int index, T[] elements) {
        checkIndex(index);

        if (elements.length + size >= data.length) {
            ensureCapacity(elements.length + size);
        }

        if (size - index > 0) {
            System.arraycopy(data, index, data, index + elements.length, size - index);
        }
        System.arraycopy(elements, 0, data, index, elements.length);

        size = size + elements.length;
        return true;
    }

    @Override
    public T get(int index) {
        return (T) data[index];
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
    public T remove(int index) {   //todo разобраться с этим, не работает
        checkIndex(index);

        T elementToRemove = (T) data[index];

        System.arraycopy(data, index + 1, data, index, size - index);
        size--;

        return elementToRemove;
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);

        T elementToReplace = (T) data[index];
        data[index] = element;

        return elementToReplace;
    }

    public int getSize() {
        return size;
    }

    public void print() {
        for (Object object : data) {
//            if (object == null) {
//                continue;
//            }

            System.out.println(object);
        }
    }
}
