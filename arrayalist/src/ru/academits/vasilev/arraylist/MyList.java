package ru.academits.vasilev.arraylist;

import java.util.Collection;
import java.util.Comparator;

public interface MyList<T> {
    void add(int index, T object);

    boolean addAll(int index, Collection<? extends T> c);

    T get(int index);

    int indexOf(Object object);

    int lastIndexOf(Object object);

    T remove(int index);

    T set(int index, T object);

    //void sort(Comparator<? super T> c);
}
