package ru.academits.vasilev.arraylist.main;

import ru.academits.vasilev.arraylist.MyArrayList;

public class ArrayListMain {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();

        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");


        list.remove("five");
        for (String item : list) {
            System.out.println(item);
        }

        System.out.println(list.size());
    }
}
