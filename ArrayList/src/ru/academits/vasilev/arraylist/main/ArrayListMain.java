package ru.academits.vasilev.arraylist.main;

import ru.academits.vasilev.arraylist.MyArrayList;

import java.util.ArrayList;

public class ArrayListMain {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();

        MyArrayList<String> list2 = new MyArrayList<>();

        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");

        for (String item : list) {
            System.out.println(item);
        }

        System.out.println();

        list.remove(0);

        for (String item : list) {
            System.out.println(item);
        }

        System.out.println(list.size());
    }
}
