package ru.academits.vasilev.arraylist;

public interface MyList<T> {
    void add(int index, T object);

    boolean addAll(int index, T[] object);

    T get(int index);

    int indexOf(T object);

    int lastIndexOf(T object);

    T remove(int index);

    T set(int index, T object);
}
