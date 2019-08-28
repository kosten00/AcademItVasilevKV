package ru.academits.vasilev.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static String openAndCloseTable(int i) {
        if (i == 0) {
            return "<table><tr><td>";
        }

        return "</table>";
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter("FileForCsvTask.html");
             Scanner scanner = new Scanner(new FileInputStream("FileForCsvTask.csv"))) {

            for (int i = 0; scanner.hasNextLine(); i++) {
                if (i == 0 || !scanner.hasNextLine()) {
                    writer.print(openAndCloseTable(i));
                }

                String s = scanner.nextLine();

                writer.print(s.replace(",", "</td><td>"));

                if (s.indexOf(',') == s.lastIndexOf(',')) {

                }

            }
        }
    }
}

//writer.print("<table><tr><td>");
//writer.print("</td></tr></table>");
//writer.println(scanner.nextLine().replaceFirst(",", "</td><td>").replaceAll("\n", "</br>"));