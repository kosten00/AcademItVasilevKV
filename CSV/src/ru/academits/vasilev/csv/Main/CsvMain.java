package ru.academits.vasilev.csv.Main;

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
    /* <!DOCTYPE html>
        <html lang="ru">
        <head>
        <meta charset="UTF-8">
        <title>Ваша страница</title>
        </head>
        <body>
        <h1>Страница</h1>
        <p>Текст</p>
        </body>
        </html> */


    public static String checkForKeySymbol(int c) {
        if (Character.toString(c).equals(",")) {
            return "comma";
        }

        if (Character.toString(c).equals("\"")) {
            return "quotes";
        }

        if (Character.toString(c).equals("\n")) {
            return "newline";
        }

        return Character.toString(c);
    }


    private static void swCaseCsvConverter(String inputCsvFile, String outputHtmlFile) throws IOException {
        try (PrintWriter writer = new PrintWriter(outputHtmlFile);
             FileReader reader = new FileReader(inputCsvFile)) {

            int c;
            boolean quotesOpened = false;
            boolean commaPutted = false;

            boolean startedTableRow = false;
            boolean lastActionTableDetailMarked = false;

            int quotesCount = 0;

            writer.print("<!DOCTYPE html><head><meta charset=\"UTF-8\"><body><table><tr><td>");


            boolean commaLastSign = false;
            boolean newLineLastSign = false;

            while ((c = reader.read()) != -1) {
                if (c == 0) {
                    quotesOpened = false;
                    commaLastSign= false;
                    newLineLastSign = false;
                }

                switch (checkForKeySymbol(c)) {
                    case ("comma"):
                        //Два условия: если внутри ковычек или снаружи.
                        if (quotesOpened) {
                            writer.print((char) c);
                            break;
                        }
                        commaLastSign = true;
                        writer.print("</td><td>");
                        quotesOpened = false;
                        break;
                    case ("quotes"):
                        //Если после запятой, то ковычки открываются
                        //Если ковычки отркыты, то закрыть
                        if (commaLastSign || newLineLastSign) {
                            quotesOpened = true;
                            break;
                        }
                        writer.print((char) c);
                        break;
                    case ("newline"):
                        if (quotesOpened) {
                            writer.print("<br/>");
                            break;
                        }
                        writer.print("</td></tr><tr><td>");
                        newLineLastSign = true;
                        break;
                    default:
                        writer.print((char) c);
                        commaLastSign = false;
                }
            }

            writer.print("</td></tr></table></body></head>");
        }
    }


    public static void main(String[] args) throws IOException {
        String inputCsvFile = "FileForCsvTask.csv";
        String outputHtmlFile = "FileForCsvTask.html";

        swCaseCsvConverter(inputCsvFile, outputHtmlFile);
    }
}