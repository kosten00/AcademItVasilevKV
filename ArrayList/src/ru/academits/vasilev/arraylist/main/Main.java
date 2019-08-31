package ru.academits.vasilev.arraylist.main;

import ru.academits.vasilev.arraylist.MyArrayList;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        Integer[] arr = {2, 3, 4};
        list.addAll(Arrays.asList(arr));
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.print();

        Integer[] arrToRemove = {4, 5, 5};

        Integer[] arrToRetain = {5, 5};

        System.out.println("size = " + list.size());
        System.out.println();

        list.removeAll(Arrays.asList(arrToRemove));

        list.print();
        System.out.println("size = " + list.size());

        list.retainAll(Arrays.asList(arrToRetain));

        list.print();

        list.removeAll(Arrays.asList(arrToRetain));

        list.print();

        list.addAll(Arrays.asList(arrToRemove));
        list.addAll(Arrays.asList(arrToRetain));

        list.print();

        while (list.iterator().hasNext()) {
            System.out.println(list.iterator().next());
        }
    }
}
