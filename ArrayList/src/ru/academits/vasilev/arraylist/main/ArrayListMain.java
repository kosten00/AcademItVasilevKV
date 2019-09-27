package ru.academits.vasilev.arraylist.main;

import ru.academits.vasilev.arraylist.MyArrayList;

import java.util.ArrayList;

public class ArrayListMain {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();

        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");


        list.remove(4);
        for (String item : list) {
            System.out.println(item);
        }

        System.out.println(list.size());

        ArrayList<String> AList= new ArrayList<>();
        AList.add("one");
        AList.add("two");
        AList.add("three");
        AList.add("four");
    }
}
