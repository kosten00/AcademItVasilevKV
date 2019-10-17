package ru.academits.vasilev.hashtable;

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private LinkedList[] table;
    private int elementsCount;
    private int tablesCount;
    private int modCount;
    private static final int DEFAULT_TABLE_SIZE = 10;

    private int getIndex(Object object, int arrayLength) {
        if (object == null) {
            return arrayLength - 1;
        }

        return Math.abs(object.hashCode() % (arrayLength - 1));
    }

    private LinkedList<T>[] fillTable(LinkedList<T>[] tableArray) {
        for (int i = 0; i < tableArray.length; i++) {
            tableArray[i] = new LinkedList<>();
        }

        return tableArray;
    }

    public MyHashTable() {
        tablesCount = DEFAULT_TABLE_SIZE;
        table = fillTable(new LinkedList[tablesCount]);

    }

    public MyHashTable(int tablesCount) {
        this.tablesCount = tablesCount;
        table = fillTable(new LinkedList[this.tablesCount]);
    }

    public void getElementsPerTable() {
        for (LinkedList list : table) {
            System.out.println(list.size());
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
        int index = getIndex(object, table.length);
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

            return (T) table[currentTableIndex].get(currentElementIndex);
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

    @Override
    public <T1> T1[] toArray(T1[] array) {
        if (elementsCount > array.length) {
            return (T1[]) Arrays.copyOf(toArray(), elementsCount, array.getClass());
        }

        int i = 0;
        for (Object element : this) {
            array[i++] = (T1) element;
        }

        if (elementsCount < array.length) {
            array[elementsCount] = null;
        }

        return array;
    }

    @Override
    public boolean add(Object object) {
        int index = getIndex(object, table.length);
        table[index].add(object);

        modCount++;
        elementsCount++;

        return true;
    }

    @Override
    public boolean remove(Object object) {
        int index = getIndex(object, table.length);
        if (table[index].remove(object)) {
            modCount++;
            elementsCount--;
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        int count = collection.size();

        for (Object object : collection) {
            if (contains(object)) {
                count--;
            }
        }

        return (count == 0) && (collection.size() != 0);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean added = false;

        for (Object object : collection) {
            added = add(object);
        }

        return added;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        int removed = 0;

        for (Object object : collection) {
            if (remove(object)) {
                removed++;
            }
        }

        return removed > 0;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        int retained = 0;

        for (LinkedList<T> list : table) {
            if (list.retainAll(collection)) {
                retained++;
            }
        }

        return retained > 0;
    }

    @Override
    public void clear() {
        for (LinkedList<T> list : table) {
            list.clear();
        }

        elementsCount = 0;
    }
}