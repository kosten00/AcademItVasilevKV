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
    private int elementsCount;
    private int tablesCount;
    private int modCount;
    private final int DEFAULT_TABLE_SIZE = 10;
    private final int RESIZE_COEFFICIENT = 2;
    private final double MAX_LOAD_FACTOR = 1;
    //private final double LOAD_FACTOR = (double) elementsCount / tablesCount;

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

    private void checkLoadFactor() {
        System.out.println("Checking load factor");
        double loadFactor = elementsCount / tablesCount;

        if (loadFactor > MAX_LOAD_FACTOR) {
            resize();

            System.out.println("jump to resize");

            //checkLoadFactor();
        }
        System.out.println("loadfactor checked");
    }

    private void resize() {
        System.out.println("Resizing");

        LinkedList<T>[] resizedTable = new LinkedList[table.length * RESIZE_COEFFICIENT];

        //TODO заполнение массива!

        LinkedList<T>[] tempTable = table;
        table = resizedTable;

        for (LinkedList<T> list : tempTable) {
            addAll(list);
        }
    }

    public MyHashTable() {
        tablesCount = DEFAULT_TABLE_SIZE;

        table = new LinkedList[tablesCount];
        for (int i = 0; i < tablesCount; i++) {
            table[i] = new LinkedList<T>();
        }

        elementsCount = 0;
    }

    public MyHashTable(int tablesCount) {
        this.tablesCount = tablesCount;

        table = new LinkedList[this.tablesCount];
        for (int i = 0; i < this.tablesCount; i++) {
            table[i] = new LinkedList<T>();
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

        int index = countIndex(object);
        table[index].add(object);

        modCount++;
        elementsCount++;

        //checkLoadFactor();
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
            int index = countIndex((T) object);

            if (!table[index].contains(object)) {
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

            elementsCount++;

            System.out.println("object from collection added, elementsCount = " + elementsCount);
        }

        modCount++;
        //elementsCount += collection.size();
        //checkLoadFactor();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection.size() == 0) {
            return false;
        }

        for (Object object : collection) {
            int objectIndex;

            if (table[(objectIndex = countIndex((T) object))].contains(object)) {
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

            if (table[(objectIndex = countIndex((T) object))].contains(object)) {
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