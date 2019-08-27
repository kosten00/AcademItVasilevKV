package ru.academits.vasilev.arraylist.main;

import ru.academits.vasilev.arraylist.MyArrayList;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();

        ArrayList list333 = new ArrayList();

        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);
        list.add(3, 4);
        list.add(4, 5);

        list.remove(1);

        list.print();

        System.out.println();
        System.out.println(list.getSize());
    }
}
