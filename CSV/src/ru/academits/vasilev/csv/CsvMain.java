package ru.academits.vasilev.csv;

import java.io.*;
import java.util.Scanner;

/*
1. Нужно, чтобы пути к файлам брались из аргументов программы -- вроде поправил
2. После &amp должна быть точка с запятой -- поправил
3. Должен быть корректный html документ, включающий: doctype, head, body, meta с кодировкой
4. На приложенном файле результат неверный
5. Программа должна обрабатывать исключения
6. Не нужно применять StringBuilder, так как файлы могут быть большими и занимать много памяти.
Нужно сразу писать в выходной файл
7. Лучше, чтобы алгоритм не использовал строковые функции (indexOf, replace, и др.)
В этой задаче лучше использовать посимвольный проход: взяли символ из входного файла, записали что-то в выходной файл
 */

public class CsvMain {
    private static void csvToHtmlConverter(String inputCsvFile, String outputHtmlFile) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(outputHtmlFile);
             Scanner scanner = new Scanner(new FileInputStream(inputCsvFile))) {

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

    /*
    <!DOCTYPE html>
        <html lang="ru">
        <head>
        <meta charset="UTF-8">
        <title>Ваша страница</title>
        </head>
        <body>
        <h1>Страница</h1>
        <p>Текст</p>
        </body>
        </html>
     */

    private static void newCsvToHtmlConverter(String inputCsvFile, String outputHtmlFile) throws IOException {
        try (PrintWriter writer = new PrintWriter(outputHtmlFile);
             FileReader reader = new FileReader(inputCsvFile)) {

            writer.print("<!DOCTYPE html><head><meta charset=\"UTF-8\"><body><table><tr><td>");
            //с боди начинается само содержимое файла

            int ch;
            while ((ch = reader.read()) != -1) {

                writer.print((char) ch);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String inputCsvFile = "FileForCsvTask.csv";
        String outputHtmlFile = "FileForCsvTask.html";

        newCsvToHtmlConverter(inputCsvFile, outputHtmlFile);
    }
}
