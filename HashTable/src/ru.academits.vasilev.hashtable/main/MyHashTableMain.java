package ru.academits.vasilev.hashtable.main;


import ru.academits.vasilev.hashtable.MyHashTable;

import java.util.Arrays;

public class MyHashTableMain {

    public static void main(String[] args) {
        MyHashTable<Integer> mht1 = new MyHashTable<>();

        mht1.add(null);
        mht1.add(null);
        mht1.add(null);
        mht1.add(null);
        mht1.add(null);
        mht1.add(null);
        mht1.add(234);
        mht1.add(756);
        mht1.add(556);
        mht1.add(980);
        mht1.add(4);
        mht1.add(34);
        mht1.add(66);
        mht1.add(123465);
        mht1.add(6);
        mht1.add(44444);
        mht1.add(34);
        mht1.add(1234);
        mht1.add(234);
        mht1.add(756);
        mht1.add(556);
        mht1.add(980);
        mht1.add(4);
        mht1.add(34);
        mht1.add(66);
        mht1.add(123465);
        mht1.add(6);
        mht1.add(44444);
        mht1.add(34);

        mht1.getElementsPerTable();

        Integer[] arr1 = {12, 32, 434, 543, 5554, 233, 11, 0, -1, -23, -2221, 0};

        System.out.println("size before addAll " + mht1.size());

        mht1.addAll(Arrays.asList(arr1));

        System.out.println("size after addAll " + mht1.size());

        System.out.println(Arrays.toString(mht1.toArray()));

        for (Integer i : mht1) {
            System.out.print(" " + i + " ");
        }
        System.out.println("size = " + mht1.size());

        System.out.println("contains : " + mht1.containsAll(Arrays.asList(arr1)));

        System.out.println("size before addAll " + mht1.size());

        mht1.removeAll(Arrays.asList(arr1));

        System.out.println("size after removeAll " + mht1.size());

        for (Integer i : mht1) {
            System.out.print(" " + i + " ");
        }
        System.out.println("size = " + mht1.size());

        mht1.clear();

        for (Integer i : mht1) {
            System.out.println(" " + i + " ");
        }

        MyHashTable<Integer> mht2 = new MyHashTable<>();

        System.out.println(mht1.addAll(mht2));
    }
}
