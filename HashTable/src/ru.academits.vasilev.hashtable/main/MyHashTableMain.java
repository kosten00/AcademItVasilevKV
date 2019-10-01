package ru.academits.vasilev.hashtable.main;

import org.w3c.dom.ls.LSOutput;

import java.util.Hashtable;

public class MyHashTableMain {

    public static void main(String[] args) {
        int[] arr = {0,1,5, 9, 0 , 77, 694967467};

        int i = Math.abs(arr.hashCode() % 10);


        System.out.println(i);
    }

}
