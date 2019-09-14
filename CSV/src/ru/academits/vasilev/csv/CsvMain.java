package ru.academits.vasilev.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CsvMain {
    public static void main(String[] args) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter("FileForCsvTask.html");
             Scanner scanner = new Scanner(new FileInputStream("FileForCsvTask.csv"))) {

            StringBuilder s = new StringBuilder("<table><tr><td>");

            while (scanner.hasNextLine()) {
                s.append(scanner.nextLine().
                        replaceAll("&", "&amp;").
                        replaceAll(">", "&gt;").
                        replaceAll("<", "&lt;"));

                while (s.indexOf(",") != -1 && (s.indexOf(",") < s.indexOf("\""))) {
                    s.replace(s.indexOf(","), s.indexOf(",") + 1, "</td><td>");
                }

                if (s.indexOf("\"") == s.lastIndexOf("\"") && s.indexOf(",") < s.indexOf("\"")) {
                    s.append("</br>");
                    s.replace(s.indexOf("\""), s.indexOf("\"") + 1, "");
                } else {
                    s.append("</td></tr><tr><td>");
                    s.replace(s.indexOf("\""), s.indexOf("\"") + 1, "");
                }

                while (s.indexOf("\"\"") != -1) {
                    s.replace(s.indexOf("\"\""), s.indexOf("\"\"") + 1, "");
                }

                while (s.indexOf(",") != -1) {
                    s.replace(s.indexOf(","), s.indexOf(",") + 1, "</td><td>");
                }

                if (!scanner.hasNextLine()) {
                    String excess = "<tr><td>";

                    s.replace(s.length() - excess.length(), s.length(), "</table>");
                }
            }
            System.out.println(s);

            writer.print(s);
        }
    }
}
