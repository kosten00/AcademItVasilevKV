package ru.academits.vasilev.tree.main;

import java.util.Comparator;

public class MyComparator<T> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        return compare(o2, o1);
    }
}