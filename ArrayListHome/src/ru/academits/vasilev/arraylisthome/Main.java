package ru.academits.vasilev.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> list1 = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("FileForArrayListHomeTask.txt"))) {
            while (scanner.hasNextLine()) {
                list1.add(Integer.parseInt(scanner.nextLine()));
            }
        }
        System.out.println(list1);

        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) % 2 != 0) {
                continue;
            }
            list1.remove(i);
            i--;
        }
        System.out.println(list1);

        ArrayList<Integer> list2 = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("FileForArrayListHomeTask.txt"))) {
            while (scanner.hasNextLine()) {
                list2.add(Integer.parseInt(scanner.nextLine()));
            }
        }
        System.out.println(list2);

        ArrayList<Integer> list3 = new ArrayList<>();

        for (Integer integer : list2) {
            if (list3.contains(integer)) {
                continue;
            }
            list3.add(integer);
        }

        System.out.println(list3);
    }
}