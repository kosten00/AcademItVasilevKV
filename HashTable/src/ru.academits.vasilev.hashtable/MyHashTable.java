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
 */

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MyHashTable<T> implements Collection<T> {
    private List[] array;
    private int size;
    private int modCount;
    private final int DEFAULT_ARRAY_SIZE = 10;

    private int countIndex(T object) {
        return Math.abs(object.hashCode() % array.length);
    }

    public MyHashTable() {
        array = new List[DEFAULT_ARRAY_SIZE];
        size = DEFAULT_ARRAY_SIZE;
    }

    public MyHashTable(int size) {
        array = new List[size];
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
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T object) {
        int index = countIndex(object);
        array[index].add(object);

        modCount++;
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
