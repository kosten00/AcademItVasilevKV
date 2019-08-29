package ru.academits.vasilev.arraylist.main;

import ru.academits.vasilev.arraylist.OldMyArrayList;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        OldMyArrayList<Integer> list = new OldMyArrayList<>();

        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);
        list.add(3, 4);
        list.add(4, 5);

        Integer[] arr = {9, 8, 6};

        list.print();

        System.out.println();

        list.trimToSize();

        list.print();

        list.add(5, 1000);

        System.out.println(list.addAll(5, arr));

        list.addLast(100500);
        list.addLast(500);

        list.print();
    }
}
