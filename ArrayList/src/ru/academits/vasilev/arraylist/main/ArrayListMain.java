package ru.academits.vasilev.arraylist.main;

import ru.academits.vasilev.arraylist.MyArrayList;

import java.util.ArrayList;

public class ArrayListMain {
    public static void main(String[] args) {
        MyArrayList<String> list1 = new MyArrayList<>();

        MyArrayList<String> list2 = new MyArrayList<>();

        list1.add("one");
        list1.add("two");
        list1.add("three");

        System.out.println(list2.retainAll(list1));

        ArrayList<String> alist = new ArrayList<>();
        alist.add("one");
        alist.add("two");

        ArrayList<String> alist2 = new ArrayList<>();

        System.out.println(alist.addAll(alist2));
    }
}