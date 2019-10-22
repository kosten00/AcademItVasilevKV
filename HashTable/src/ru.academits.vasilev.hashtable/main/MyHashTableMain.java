package ru.academits.vasilev.hashtable.main;


import ru.academits.vasilev.hashtable.MyHashTable;

import java.util.Arrays;
import java.util.LinkedList;

public class MyHashTableMain {

    public static void main(String[] args) {
        MyHashTable<String> mht1 = new MyHashTable<>();

        mht1.add("c");

        mht1.add(null);
        mht1.add(null);
        mht1.add(null);
        mht1.add(null);
        mht1.add(null);
        mht1.add("a");
        mht1.add("b");

        for (String s : mht1) {
            System.out.println(s);
        }

        String[] arr = new String[]{"a", "b", "c", "d", "e", "f", "g"};

        String[] arrT = arr;

        //mht1.toArray(arr);

        System.out.println(Arrays.toString(arrT));

        MyHashTable<String> mht2 = new MyHashTable<>();

        System.out.println(mht1.containsAll(mht2));

        //mht2.addAll(Arrays.asList(arr));

        //System.out.println("now printing mht2");

        //mht2.forEach(System.out::println);

        //System.out.println("now retainAll");

        //System.out.println(mht1.retainAll(mht2));

        //System.out.println("now checking mht1 size" + mht1.size());

        //mht1.forEach(System.out::println);
    }
}
