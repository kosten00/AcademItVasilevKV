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
        ArrayList<Integer> list2 = new ArrayList<>(list1);

        System.out.println(list1);

        //System.out.println(list1.get(3));

        for (int i = 0; i < list1.size(); i++) {
            int temp = list1.get(i);
            if (temp % 2 == 0) {
                list1.remove(i);
            }
        }

        System.out.println(list1);
    }
}
