package ru.academits.vasilev.arraylist.main;

import ru.academits.vasilev.arraylist.MyArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();

        list.add(null);

        System.out.println(list.lastIndexOf(null));

    }
}
