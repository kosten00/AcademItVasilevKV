package ru.academits.vasilev.hashtable.main;


import ru.academits.vasilev.hashtable.MyHashTable;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class MyHashTableMain {

    public static void main(String[] args) {
        MyHashTable<Integer> mht1 = new MyHashTable<>(9);

        Integer n = 123;

        Integer[] arr1 = {12, 32, 434, 543, 5554, 233, 11, 0, -1, -23, -2221, 0};

        //LinkedList<Integer> ll1 = new LinkedList<>();
        // ll1.add(n);

        mht1.addAll(Arrays.asList(arr1));

        System.out.println();

        for (Integer i : mht1) {
            System.out.println(i + " " + mht1.size());
        }
    }
}
