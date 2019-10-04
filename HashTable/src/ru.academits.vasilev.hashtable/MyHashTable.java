package ru.academits.vasilev.hashtable;

/*
HashTable
Лекции, нужные для решения задачи: 1-8, 10 понятие итератора, 13.
Сделать свою реализацию хэш-таблицы, сделать ее generic’ом.
Класс должен реализовать интерфейс Collection<T> (ICollection<T> в C#).
И в одном из конструкторов сделать параметр, который задает размер массива хэш-таблицы.
Чему научитесь:
Понимание работы хэш-таблицы
Реализация интересного итератора
Generic’и
ЧИСЛО КОЛЛИЗИЙ - КОЛ-ВО ЭЛЕМЕНТОВ СПИСКА, ПРИ КОТОРОМ ПЕРЕСТРАИВАЕТСЯ ТАБЛИЦА, НАДО УТОЧНИТЬ СКОКА
 */

import java.util.*;

public class MyHashTable<T> implements Collection<T> {
    private LinkedList<T>[] table;
    private int tablesCount;
    private int elementsCount;
    private int modCount;
    //private final int DEFAULT_TABLE_SIZE = 10;
    //число, при котором вся таблица перестраивается, формируется исходя из губины вложенных списков.

    private int countIndex(T object) {
        return Math.abs(object.hashCode() % table.length);
    }

    private void checkNullEquality(T object) {
        if (object == null) {
            throw new IllegalArgumentException("Can't add objects = null");
        }
    }

    private void checkLength(Collection<?> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Can't add objects = null");
        }

        if (collection.size() == 0) {
            throw new IllegalArgumentException("Can't add empty collection");
        }
    }

    public MyHashTable() {
        tablesCount = 10;
        table = new LinkedList[tablesCount];

        for (int i = 0; i < tablesCount; i++) {
            table[i] = new LinkedList<>();
        }
        elementsCount = 0;
    }

    public MyHashTable(int tablesCount) {
        this.tablesCount = tablesCount;
        table = new LinkedList[tablesCount];

        for (int i = 0; i < tablesCount; i++) {
            table[i] = new LinkedList<>();
        }
        elementsCount = 0;
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

        int index = countIndex((T) object);
        return table[index].contains(object);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    //еще пока не делал
    private class MyIterator implements Iterator<T> {
        private int currentElementIndex = -1;
        private int currentTableIndex = 0;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentElementIndex + 1 < elementsCount;
        }

        @Override
        public T next() { //// ТУТА НАДО ПОДУМАТЬ!!
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Concurrent list modification during iteration through! ");
            }

            if (currentElementIndex + 1 == table[currentTableIndex].size()) {
                currentElementIndex = -1;
                currentTableIndex++;
            }
            currentElementIndex++;

            if (currentElementIndex >= elementsCount) {
                throw new NoSuchElementException("Index is out of the list's size");
            }

            if (currentTableIndex >= table.length) {
                throw new ConcurrentModificationException("Concurrent list size modification during iteration through! ");
            }

            return table[currentTableIndex].get(currentElementIndex);
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[tablesCount];

        System.arraycopy(table, 0, array, 0, tablesCount);

        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        return array;
    }

    @Override
    public boolean add(T object) {
        checkNullEquality(object);

        int index = countIndex(object);
        table[index].add(object);

        modCount++;
        elementsCount++;
        return true;
    }

    @Override
    public boolean remove(Object object) {
        checkNullEquality((T) object);

        int index = countIndex((T) object);

        if (table[index].remove(object)) {
            modCount++;
            elementsCount--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        checkLength(collection);

        for (Object object : collection) {
            if (!table[countIndex((T) object)].contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        checkLength(collection);


        for (Object object : collection) {
            int index = countIndex((T) object);

            table[index].add((T) object);
        }

        modCount++;
        elementsCount += collection.size();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (!containsAll(collection)) {
            return false;
        }

        modCount++;
        elementsCount -= collection.size();

        for (Object object : collection) {
            table[countIndex((T) object)].remove(object);
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (!containsAll(collection)) {
            return false;
        }
        LinkedList<T> singleObjectCollection = new LinkedList<>();

        modCount++;
        elementsCount = collection.size();

        int i = 0;
        for (Object object : collection) {
            singleObjectCollection.add((T) object);

            table[i].retainAll(singleObjectCollection);
            i++;
        }

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
