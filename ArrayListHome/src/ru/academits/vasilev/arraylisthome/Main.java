package ru.academits.vasilev.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("C:\\Users\\я\\IdeaProjects\\AcademItVasilevKV\\ArrayListHome\\src\\ru\\academits\\vasilev\\arraylisthome\\some_file.txt"))) {
            while (scanner.hasNextLine()) {
                list1.add(Integer.parseInt(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(list1);

        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) % 2 != 0 || list1.get(i) == 0) {
                continue;
            }
            list1.remove(i);
            i--;
        }
        System.out.println(list1);

        ArrayList<Integer> list2 = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("C:\\Users\\я\\IdeaProjects\\AcademItVasilevKV\\ArrayListHome\\src\\ru\\academits\\vasilev\\arraylisthome\\some_file.txt"))) {
            while (scanner.hasNextLine()) {
                list2.add(Integer.parseInt(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(list2);

        ArrayList<Integer> list3 = new ArrayList<>();

        for (int i = 0; i < list2.size(); i++) {
            boolean duplicated = false;

            for (int j = 0; j < i; j++) {
                if (list2.get(j).equals(list2.get(i))) {
                    duplicated = true;

                    break;
                }
            }

            if (duplicated) {
                continue;
            }

            list3.add(list2.get(i));
        }

        System.out.println(list3);
    }
}