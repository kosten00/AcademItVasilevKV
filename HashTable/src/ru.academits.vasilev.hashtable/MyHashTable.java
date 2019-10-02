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
    private int size;
    private int modCount;
    private final int DEFAULT_ARRAY_SIZE = 10;
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
        table = new LinkedList[DEFAULT_ARRAY_SIZE];
        size = DEFAULT_ARRAY_SIZE;
    }

    public MyHashTable(int size) {
        table = new LinkedList[size];
        this.size = size;
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
    public boolean contains(Object object) {
        checkNullEquality((T) object);

        int index = countIndex((T) object);
        return table[index].contains(object);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    //еще пока не делал
    private class MyIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Concurrent list modification during iteration through! ");
            }
            currentIndex++;

            if (currentIndex >= size) {
                throw new NoSuchElementException("Index is out of the list's size");
            }

            if (currentIndex >= table.length) {
                throw new ConcurrentModificationException("Concurrent list size modification during iteration through! ");
            }
            return (T) table[currentIndex];
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        System.arraycopy(table, 0, array, 0, size);

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
        size++;
        return true;
    }

    @Override
    public boolean remove(Object object) {
        checkNullEquality((T) object);

        int index = countIndex((T) object);

        if (table[index].remove(object)) {
            modCount++;
            size--;
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
            table[countIndex((T) object)].add((T) object);
        }

        modCount++;
        size += collection.size();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (!containsAll(collection)) {
            return false;
        }

        modCount++;
        size -= collection.size();

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
        size = collection.size();

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
    }
}
