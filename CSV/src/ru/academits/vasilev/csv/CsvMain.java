package ru.academits.vasilev.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class CsvMain {

    public static void main(String[] args) throws FileNotFoundException {

        try (PrintWriter writer = new PrintWriter("FileForCsvTask.html");
             Scanner scanner = new Scanner(new FileInputStream("FileForCsvTask.csv"))) {

            StringBuilder s = new StringBuilder();

            for (int i = 0; scanner.hasNextLine(); i++) {
                if (i == 0) {
                    s = new StringBuilder("<table><tr><td>");
                }
                s.append(scanner.nextLine());

                while (s.indexOf(",") != -1 && (s.indexOf(",") < s.indexOf("\""))) {
                    s.replace(s.indexOf(","), s.indexOf(",") + 1, "</td><td>");
                }

                if (s.indexOf("\"") == s.lastIndexOf("\"")) {
                    s.append("</br>");
                    s.replace(s.indexOf("\""), s.indexOf("\"") + 1, "");
                }

                if (!scanner.hasNextLine()) {
                    s.append("</td></tr></table>");
                }

            }

            System.out.println(s);
        }
        ;


    }
}
