package ru.academits.vasilev.hashtable;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private LinkedList<T>[] table;
    private int elementsCount;
    private int tablesCount;
    private int modCount;
    private final int DEFAULT_TABLE_SIZE = 10;
    private final int RESIZE_COEFFICIENT = 2;

    private int countIndex(T object, int arrayLength) {
        return Math.abs(object.hashCode() % arrayLength);
    }

    private void checkNullEquality(T object) {
        if (object == null) {
            throw new IllegalArgumentException("Can't add null object");
        }
    }

    private void checkNullEquality(Collection<?> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Can't add null collection");
        }
    }

    private void checkLoadFactor() {
        if (tablesCount <= elementsCount) {
            resize();
        }
    }

    private void resize(int size) {
        T[] array = (T[]) this.toArray();

        int sizeAfterAddingCollection = (int) Math.ceil((double) size / DEFAULT_TABLE_SIZE);
        table = fillTable(new LinkedList[tablesCount = sizeAfterAddingCollection]);
        elementsCount = 0;

        for (int i = 0; i < array.length; i++) {
            this.add(array[i]);
        }
    }

    private void resize() {
        T[] array = (T[]) this.toArray();
        table = fillTable(new LinkedList[tablesCount = tablesCount * RESIZE_COEFFICIENT]);
        elementsCount = 0;

        for (int i = 0; i < array.length; i++) {
            this.add(array[i]);
        }
    }

    private LinkedList<T>[] fillTable(LinkedList<T>[] tableArray) {
        for (int i = 0; i < tableArray.length; i++) {
            tableArray[i] = new LinkedList<>();
        }

        return tableArray;
    }

    public MyHashTable() {
        table = fillTable(new LinkedList[tablesCount = DEFAULT_TABLE_SIZE]);
    }

    public MyHashTable(int tablesCount) {
        table = fillTable(new LinkedList[this.tablesCount = tablesCount]);
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
        checkNullEquality((T) object);

        int index = countIndex((T) object, table.length);
        return table[index].contains(object);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int currentElementIndex = -1;
        private int currentTableIndex = 0;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            if (!isLastInTable()) {
                return true;
            }

            if (hasNextTable()) {
                currentTableIndex++;
                currentElementIndex = -1;

                return hasNext();
            }

            return false;
        }

        private boolean hasNextTable() {
            return currentTableIndex + 1 < tablesCount;
        }

        private boolean isLastInTable() {
            return currentElementIndex + 1 == table[currentTableIndex].size();
        }

        @Override
        public T next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Concurrent list modification during iteration through! ");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Index is out of the hashtable size");
            }
            currentElementIndex++;

            return table[currentTableIndex].get(currentElementIndex);
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[elementsCount];

        if (elementsCount > 0) {
            int i = 0;
            for (T element : this) {
                array[i] = element;
                i++;
            }
        }

        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        if (elementsCount > array.length) {
            return (T1[]) Arrays.copyOf(toArray(), elementsCount);
        }

        return (T1[]) Arrays.copyOf(toArray(), array.length);
    }

    @Override
    public boolean add(T object) {
        checkNullEquality(object);
        checkLoadFactor();

        int index = countIndex(object, table.length);
        table[index].add(object);

        modCount++;
        elementsCount++;

        return true;
    }

    @Override
    public boolean remove(Object object) {
        checkNullEquality((T) object);

        int index = countIndex((T) object, table.length);
        if (table[index].remove(object)) {
            modCount++;
            elementsCount--;
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        checkNullEquality(collection);

        if (collection.size() == 0) {
            return false;
        }

        for (Object object : collection) {
            int index = countIndex((T) object, table.length);

            if (!table[index].contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        checkNullEquality(collection);

        if (collection.size() == 0) {
            return false;
        }

        if (collection.size() + elementsCount >= tablesCount) {
            resize(collection.size() + elementsCount);
        }

        for (Object object : collection) {
            int index = countIndex((T) object, table.length);
            table[index].add((T) object);
            elementsCount++;
        }

        modCount++;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection.size() == 0) {
            return false;
        }

        for (Object object : collection) {
            int objectIndex;

            if (table[(objectIndex = countIndex((T) object, table.length))].contains(object)) {
                for (int i = 0; i < table[objectIndex].size(); i++) {
                    if (Objects.equals(object, table[objectIndex].get(i))) {
                        table[objectIndex].remove(i);
                        elementsCount--;
                        i--;
                    }
                }
            }
        }

        modCount++;
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection.size() == 0) {
            return false;
        }

        for (Object object : collection) {
            int objectIndex;

            if (table[(objectIndex = countIndex((T) object, table.length))].contains(object)) {
                for (int i = 0; i < table[objectIndex].size(); i++) {
                    if (!Objects.equals(object, table[objectIndex].get(i))) {
                        table[objectIndex].remove(i);
                        elementsCount--;

                        i--;
                    }
                }
            }
        }

        modCount++;
        return true;
    }

    @Override
    public void clear() {
        for (LinkedList<T> list : table) {
            list.clear();
        }

        elementsCount = 0;
    }
}