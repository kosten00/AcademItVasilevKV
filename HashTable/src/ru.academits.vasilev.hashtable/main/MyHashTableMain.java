package ru.academits.vasilev.hashtable.main;


import ru.academits.vasilev.hashtable.MyHashTable;

import java.util.Arrays;

public class MyHashTableMain {

    public static void main(String[] args) {
        MyHashTable<String> mht1 = new MyHashTable<>();

        mht1.add("234");
        mht1.add("756");

        String[] arr = new String[]{"a", "b", "c", "d", "e", "f", "g"};

        String[] arrT = arr;

        mht1.toArray(arr);

        System.out.println(Arrays.toString(arrT));
    }
}
