package ru.academits.vasilev.arraylist.main;

import ru.academits.vasilev.arraylist.MyArrayList;

import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();

        list.add(1);

        Integer[] arr = {2, 3, 4};

        list.addAll(Arrays.asList(arr));

        list.print();

        list.removeAll(Arrays.asList(arr));

        list.print();
    }
}
