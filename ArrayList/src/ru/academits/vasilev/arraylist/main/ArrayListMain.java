package ru.academits.vasilev.arraylist.main;

import ru.academits.vasilev.arraylist.MyArrayList;

import java.util.ArrayList;
import java.util.List;

public class ArrayListMain {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();

        MyArrayList<String> list2 = new MyArrayList<>();

        list.add("one");
        list.add("two");
        list.add("three");
        list2.add("four");
        list2.add("five");
        list2.add("six");
        System.out.println("List1 :");

        for (String item : list) {
            System.out.println(item);
        }
        System.out.println();
        System.out.println("List2: ");
        for (String item : list2) {
            System.out.println(item);
        }
        System.out.println();
        list2.addAll(0, list);

        System.out.println("New list2: ");
        for (String item : list2) {
            System.out.println(item);
        }

        MyArrayList<String> list3 = new MyArrayList<>();
        list3.add("eight");
        list3.add("seven");

        System.out.println(list2.size());
        //System.out.println(list2.retainAll(list2));
        System.out.println(list2.remove("eight"));
        System.out.println(list2.size());

        for (String item : list2) {
            System.out.println(item);
        }
    }
}