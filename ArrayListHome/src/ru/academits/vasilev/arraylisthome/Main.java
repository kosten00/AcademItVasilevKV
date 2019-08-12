package ru.academits.vasilev.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> oddNumbersList = new ArrayList<>();
        ArrayList<Integer> numbersNotRepeatedList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("C:\\Users\\я\\IdeaProjects\\AcademItVasilevKV\\ArrayListHome\\src\\ru\\academits\\vasilev\\arraylisthome\\some_file.txt"))) {
            while (scanner.hasNextLine()) {
                oddNumbersList.add(Integer.parseInt(scanner.next()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(oddNumbersList);
    }
}
