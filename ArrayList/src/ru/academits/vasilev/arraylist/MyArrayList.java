package ru.academits.vasilev.arraylist;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 10;

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index " + index + " is out of the list size = " + size);
        }
    }

    private void checkSize() {
        if (size == items.length) {
            growCapacity();
        }
    }

    private void growCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public MyArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        items = (T[]) new Object[capacity];
    }

    public void ensureCapacity(int capacity) {
        items = Arrays.copyOf(items, capacity);
    }

    public void trimToSize() {
        items = Arrays.copyOf(items, size);
    }

    public void addFirst(T item) {
        checkSize();

        System.arraycopy(items, 0, items, 1, size);

        items[0] = item;
        modCount++;
        size++;
    }

    public void addLast(T item) {
        checkSize();

        items[size] = item;
        modCount++;
        size++;
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
    public boolean contains(Object item) {
        return indexOf(item) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        int currentIndex = -1;
        int expectedModCount = modCount;

        MyIterator() {
        }

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }

            currentIndex++;

            if (currentIndex >= size) {
                throw new NoSuchElementException();
            }

            if (currentIndex >= items.length) {
                throw new ConcurrentModificationException();
            }

            return items[currentIndex];
        }

        public Iterator<T> iterator() {
            return new MyIterator();
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            return (T1[]) Arrays.copyOf(items, size, a.getClass());
        }

        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(T item) {
        addLast(item);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        System.arraycopy(items, indexOf(o) + 1, items, indexOf(o), size - indexOf(o));
        modCount++;
        size--;

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        addAll(size, c);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIndex(index);

        Object[] a = c.toArray();

        if (a.length + size >= items.length) {
            ensureCapacity(a.length + size);
        }

        if (size - index > 0) {
            System.arraycopy(items, index, items, index + a.length, size - index);
        }
        System.arraycopy(a, 0, items, index, a.length);

        modCount++;
        size = size + a.length;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int start = getStartIndex(c.toArray());

        if (start == -1) {
            return false;
        }

        int end = start + c.size();

        System.arraycopy(items, end, items, start, size - end);
        int sizeBeforeChange = size;
        size = size - c.size();
        removeExcessItems(sizeBeforeChange);

        modCount++;
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int start = getStartIndex(c.toArray());

        if (start == -1) {
            return false;
        }

        int end = start + c.size();

        System.arraycopy(items, start, items, 0, end - start);
        int sizeBeforeChange = size;
        size = c.size();
        removeExcessItems(sizeBeforeChange);

        modCount++;
        return true;
    }

    private int getStartIndex(Object[] a) {
        return Collections.indexOfSubList(Arrays.asList(items), Arrays.asList(a));
    }

    private void removeExcessItems(int sizeBeforeChange) {
        int i = size;

        for (; i < sizeBeforeChange; i++) {
            items[i] = null;
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        modCount++;
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        T oldItem = items[index];

        add(index, element);
        return oldItem;
    }

    @Override
    public void add(int index, T element) {
        if (index == 0) {
            addFirst(element);
        } else {
            checkIndex(index);
            checkSize();

            System.arraycopy(items, index, items, index + 1, size - index);

            modCount++;
            items[index] = element;
            size++;
        }

    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T elementToRemove = items[index];

        System.arraycopy(items, index + 1, items, index, size - index);
        modCount++;
        size--;

        return elementToRemove;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < items.length; i++) {
            if (o.equals(items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = items.length - 1; i >= 0; i--) {
            if (o.equals(items[i])) {
                return i;
            }
        }

        return -1;
    }

    public void print() {
        int i = -1;

        for (T t : items) {
//            if (object == null) {
//                continue;
//            }
            i++;
            System.out.println(i + ": " + t);
        }
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
