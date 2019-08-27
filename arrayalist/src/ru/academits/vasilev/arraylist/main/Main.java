package ru.academits.vasilev.arraylist.main;

import ru.academits.vasilev.arraylist.MyArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();

        list.addFirst(5);

        list.add(1, 1);
        list.add(2, 2);
        list.add(3, 3);
        list.add(4, 4);

        list.addLast(10);

        Integer[] arr = {7, 6, 5, 4, 3};

        list.addAll(4, arr);

        list.set(3, 666);

        list.remove(4);

        list.print();

        System.out.println();
        System.out.println(list.getSize());
    }
}
