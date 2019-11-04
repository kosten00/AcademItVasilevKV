package ru.academits.vasilev.hashtable.main;

import ru.academits.vasilev.hashtable.MyHashTable;

import java.util.Arrays;
import java.util.HashMap;

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

        String[] arr = new String[]{"a", "b", "c", "d", "e", "f", "g"};

        mht1.toArray(arr);

        System.out.println(Arrays.toString(arr));

        MyHashTable<String> mht2 = new MyHashTable<>();

        mht1.addAll(Arrays.asList(arr));

        System.out.println("mht1 before retain: ");
        mht1.forEach(System.out::println);

        System.out.println(mht1.retainAll(mht2));

        mht2.addAll(Arrays.asList(arr));

        System.out.println("now printing mht2");
        mht2.forEach(System.out::println);

        System.out.println("mht1: ");
        mht1.forEach(System.out::println);

        System.out.println("now retainAll");
        System.out.println(mht1.retainAll(mht2));

        System.out.println("now checking mht1 size" + mht1.size());
        mht1.forEach(System.out::println);
    }
}
