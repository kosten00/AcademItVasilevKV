package ru.academits.vasilev.hashtable;

import java.util.*;

/*
Также нужно заглушить warning в первой ветке toArray(T1[]) // заглушил весь метод
4. warning в fillTable // непонятно
10. addAll - в текущем варианте будет работать, но если рассматривать в общем виде, то функция выдает неверный boolean
11. removeAll должен удалять все вхождения.
И вместо removed можно использовать булеву переменную
 */

public class MyHashTable<T> implements Collection<T> {
    private LinkedList<T>[] table;
    private int elementsCount;
    private int tablesCount;
    private int modCount;
    private static final int DEFAULT_TABLE_SIZE = 10;

    private int getIndex(Object object, int arrayLength) {
        if (object == null) {
            return 0;
        }

        return (Math.abs(object.hashCode() % (arrayLength - 1))) + 1;
    }

    private LinkedList<T>[] fillTable(LinkedList<T>[] tableArray) {
        for (int i = 0; i < tableArray.length; i++) {
            tableArray[i] = new LinkedList<>();
        }

        return tableArray;
    }

    @SuppressWarnings("unchecked")
    public MyHashTable() {
        tablesCount = DEFAULT_TABLE_SIZE;
        table = fillTable(new LinkedList[tablesCount]);
    }

    @SuppressWarnings("unchecked")
    public MyHashTable(int tablesCount) {
        if (tablesCount <= 0) {
            throw new IllegalArgumentException("Can't create table with size = 0!");
        }

        this.tablesCount = tablesCount;
        table = fillTable(new LinkedList[this.tablesCount]);
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
        private int currentIndexThisTable = -1;
        private int currentElementIndex = -1;
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
            return currentIndexThisTable + 1 == table[currentTableIndex].size();
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
                currentIndexThisTable = -1;

                return next();
            }

            currentIndexThisTable++;

            currentElementIndex++;

            return table[currentTableIndex].get(currentIndexThisTable);
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
    public boolean add(Object object) {
        int index = getIndex(object, table.length);
        table[index].add((T) object);

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
        if (collection.size() == 0) {
            return false;
        }
        int count = collection.size();

        for (Object object : collection) {
            if (contains(object)) {
                count--;
            }
        }

        return count == 0;
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
        boolean retained = false;
        int listSizeAfterRetain = 0;

        for (LinkedList<T> list : table) {
            if (list.retainAll(collection)) {
                retained = true;
            }

            listSizeAfterRetain += list.size();
        }
        elementsCount = listSizeAfterRetain;
        modCount++;

        return retained;
    }

    @Override
    public void clear() {
        for (LinkedList<T> list : table) {
            list.clear();
        }

        modCount++;
        elementsCount = 0;
    }
}