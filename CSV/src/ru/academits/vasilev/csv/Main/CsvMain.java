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


    public static String checkForKeySymbol(String s) {
        if (s.equals(",")) {
            return "comma";
        }

        if (s.equals("\"")) {
            return "quotes";
        }

        if (s.equals("\n")) {
            return "newline";
        }

        return s;
    }

    private static void swCaseCsvConverter(String inputCsvFile, String outputHtmlFile) throws IOException {
        try (PrintWriter writer = new PrintWriter(outputHtmlFile);
             FileReader reader = new FileReader(inputCsvFile)) {

            int c;

            writer.print("<!DOCTYPE html><head><meta charset=\"UTF-8\"><body><table><tr><td>");

            boolean commaLastSign = false;
            boolean newLineLastSign = true;
            boolean quotesLastSign = false;
            boolean quotesOpened = false;

            while ((c = reader.read()) != -1) {
                switch (checkForKeySymbol(Character.toString(c))) {
                    case ("comma"):
                        System.out.println("\n" + " char catch in case: " + Character.toString(c) + " ");
                        System.out.println(" got comma: ");

                        if (quotesOpened && !quotesLastSign) {

                            System.out.println(" quotes are opened and quotes not last sign ");

                            writer.print((char) c);

                            System.out.println(" printing comma " + Character.toString(c));
                            quotesOpened = false;

                            System.out.println(" setting quotes closed ");
                            break;
                        }

                        System.out.println(" quotes opened status: " + quotesOpened + " quotes last sign status: " + quotesLastSign);
                        writer.print("</td><td>");

                        if (quotesLastSign) {
                            quotesOpened = false;
                            System.out.println(" quotes was last sign before comma, closing quotes, going further this case ");
                        }

                        commaLastSign = true;

                        System.out.println(" setting comma as last sign ");
                        System.out.println(" closing and opening details ");

                        break;
                    case ("quotes"):
                        System.out.println("\n" + " char catch in case: " + Character.toString(c) + " ");
                        System.out.println(" got quotes ");

                        if ((commaLastSign || newLineLastSign) && !quotesOpened) {
                            System.out.println(" setting quotes last sign, setting quotes opened, reason: quotes not opened, comma - " + commaLastSign + ", new line - " + newLineLastSign + " ");
                            quotesOpened = true;
                            quotesLastSign = true;
                            break;
                        }
                        if (!quotesLastSign) {
                            quotesLastSign = true;
                            System.out.println(" last sign was not quotes, matching quotes as last sign, not printing quotes breaking next character ");
                            break;
                        }
                        if (quotesLastSign /*&& !quotesOpened*/) {
                            System.out.println(" quotes was last sing, printing quotes,  setting quotes last sign fals, breaking to next char ");
                            writer.print((char) c);
                            quotesLastSign = false;
                            break;
                        }
                        writer.print((char) c);
                        quotesLastSign = true;
                        System.out.println(" printing quotes" + Character.toString(c) + "setting quotes last sign true ");
                        break;
                    case ("newline"):
                        System.out.println("\n" + " char catch in case: " + Character.toString(c) + " ");
                        System.out.println(" got new line ");

                        if (quotesOpened) {
                            System.out.println(" quotes are opened, printing /br ");
                            writer.print("<br/>");
                            break;
                        }
                        System.out.println(" quotes are closed, printing tdtr, setting new line last sign as true ");
                        writer.print("</td></tr><tr><td>");


                        if (quotesOpened) {
                            System.out.println(" quotes are opened, closing quotes ");

                            quotesOpened = false;
                        }

                        newLineLastSign = true;
                        break;
                    default:
                        writer.print((char) c);
                        commaLastSign = false;
                        newLineLastSign = false;
                        quotesLastSign = false;
                        System.out.print(Character.toString(c)/* + " last signs to false; quotes opened? - " + quotesOpened*/);
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