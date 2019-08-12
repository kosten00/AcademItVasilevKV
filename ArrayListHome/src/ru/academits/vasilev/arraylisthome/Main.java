package ru.academits.vasilev.arraylisthome;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> oddNumbersList = new ArrayList<>();
        ArrayList<Integer> numbersNotRepeatedList = new ArrayList<>();

        try (Scanner scanner = new Scanner("some_file.txt")) {
            String fromFile;

            while ((fromFile = scanner.nextLine()) != null) {
                oddNumbersList.add(Integer.parseInt(fromFile));
            }
        }

        System.out.println();


    }
}
