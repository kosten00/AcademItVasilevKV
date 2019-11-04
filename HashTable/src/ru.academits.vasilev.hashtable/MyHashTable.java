package ru.academits.vasilev.hashtable;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private ArrayList<T>[] table;
    private int elementsCount;
    private int tablesCount;
    private int modCount;
    private static final int DEFAULT_TABLE_SIZE = 10;

    private int getIndex(Object object) {
        if (object == null) {
            return 0;
        }

        return Math.abs(object.hashCode() % table.length);
    }

    @SuppressWarnings("unchecked")
    public MyHashTable() {
        tablesCount = DEFAULT_TABLE_SIZE;

        table = new ArrayList[this.tablesCount];
        for (int i = 0; i < tablesCount; i++) {
            table[i] = new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public MyHashTable(int tablesCount) {
        if (tablesCount <= 0) {
            throw new IllegalArgumentException("Can't create table with size 0 or less!");
        }

        this.tablesCount = tablesCount;

        table = new ArrayList[this.tablesCount];
        for (int i = 0; i < tablesCount; i++) {
            table[i] = new ArrayList<>();
        }
    }

    @Override
    public int size() {
        return elementsCount;
    }

    @Override
    public boolean isEmpty() {
        return elementsCount == 0;
    }

    @Override
    public boolean contains(Object object) {
        int index = getIndex(object);
        return table[index].contains(object);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int currentElementIndex = -1;
        private int currentIndexInTable = -1;
        private int currentTableIndex = 0;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentElementIndex + 1 < elementsCount;
        }

        private boolean hasNextTable() {
            return currentTableIndex + 1 < tablesCount;
        }

        private boolean isLastInTable() {
            return currentIndexInTable + 1 == table[currentTableIndex].size();
        }

        @Override
        public T next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Concurrent list modification during iteration through! ");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Index is out of the hashtable size");
            }

            if (isLastInTable() && hasNextTable()) {
                currentTableIndex++;
                currentIndexInTable = -1;

                return next();
            }

            currentIndexInTable++;
            currentElementIndex++;

            return table[currentTableIndex].get(currentIndexInTable);
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[elementsCount];

        int i = 0;
        for (T element : this) {
            array[i] = element;
            i++;
        }

        return array;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T1> T1[] toArray(T1[] array) {
        if (elementsCount > array.length) {
            return (T1[]) Arrays.copyOf(toArray(), elementsCount, array.getClass());
        }

        int i = 0;
        for (Object element : this) {
            array[i] = (T1) element;

            i++;
        }

        if (elementsCount < array.length) {
            array[elementsCount] = null;
        }

        return array;
    }

    @Override
    public boolean add(T object) {
        int index = getIndex(object);
        table[index].add(object);

        modCount++;
        elementsCount++;

        return true;
    }

    @Override
    public boolean remove(Object object) {
        int index = getIndex(object);
        if (table[index].remove(object)) {
            modCount++;
            elementsCount--;
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object object : collection) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection.size() == 0) {
            return false;
        }

        boolean added = false;

        for (T object : collection) {
            if (add(object)) {
                added = true;
            }
        }

        return added;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean removed = false;
        int listSizeAfterRemove = 0;

        for (ArrayList<T> list : table) {
            if (list.removeAll(collection)) {
                removed = true;
            }

            listSizeAfterRemove += list.size();
        }

        if (removed) {
            modCount++;
        }
        elementsCount = listSizeAfterRemove;

        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean retained = false;
        int listSizeAfterRetain = 0;

        for (ArrayList<T> list : table) {
            if (list.retainAll(collection)) {
                retained = true;
            }

            listSizeAfterRetain += list.size();
        }

        if (retained) {
            modCount++;
        }
        elementsCount = listSizeAfterRetain;

        return retained;
    }

    @Override
    public void clear() {
        for (ArrayList<T> list : table) {
            list.clear();
        }

        modCount++;
        elementsCount = 0;
    }
}