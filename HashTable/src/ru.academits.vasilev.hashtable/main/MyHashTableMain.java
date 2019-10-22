package ru.academits.vasilev.hashtable.main;


import ru.academits.vasilev.hashtable.MyHashTable;

import java.util.Arrays;
import java.util.LinkedList;

public class MyHashTableMain {

    public static void main(String[] args) {
        MyHashTable<String> mht1 = new MyHashTable<>();

        mht1.add(null);
        mht1.add(null);
        mht1.add(null);
        mht1.add(null);
        mht1.add(null);
        mht1.add("234");
        mht1.add("756");

        for (String s : mht1) {
            System.out.println(s);
        }

        String[] arr = new String[]{"a", "b", "c", "d", "e", "f", "g"};

        String[] arrT = arr;

        mht1.toArray(arr);

        System.out.println(Arrays.toString(arrT));

        MyHashTable<Integer> mht2 = new MyHashTable<>(1);
    }
}
