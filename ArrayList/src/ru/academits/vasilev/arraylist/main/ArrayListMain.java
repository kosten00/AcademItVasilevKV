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
        list.add(null);

        list2.add("one");
        list2.add("two");
        list2.add("three");

        list.set(3, null);

        System.out.println(list.addAll(5, list2));

        //System.out.println(list.retainAll(list2));

        for (String item : list) {
            System.out.println(item);
        }



        ArrayList<String> AList= new ArrayList<>();

//        AList.addAll(list2);
//        AList.add("one");
//        AList.add("two");
//        AList.add("three");
//        AList.add("four");
    }
}
