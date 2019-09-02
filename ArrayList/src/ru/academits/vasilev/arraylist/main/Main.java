package ru.academits.vasilev.arraylist.main;

import ru.academits.vasilev.arraylist.MyArrayList;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();

        Integer[] arr1 = {1, 2, 3, 4, 5};
        Integer[] arr2 = {3, 7, 6, 9};
        Integer[] arr3 = {9, 9, 9};

        list.addAll(Arrays.asList(arr1));
        list.addAll(Arrays.asList(arr2));
        list.addAll(Arrays.asList(arr3));

        list.print();
        System.out.println("size = " + list.size());
        System.out.println();

        list.retainAll(Arrays.asList(arr1));
        list.print();
        System.out.println(list.size());

        list.addAll(Arrays.asList(arr2));
        list.addAll(Arrays.asList(arr3));
        list.print();
        System.out.println();

        list.removeAll(Arrays.asList(arr3));
        list.removeAll(Arrays.asList(arr1));
        list.print();
    }
}
