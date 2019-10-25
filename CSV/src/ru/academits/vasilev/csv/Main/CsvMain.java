package ru.academits.vasilev.csv.Main;

import java.io.*;

public class CsvMain {
    private static void convertToCsv(String inputCsvFile, String outputHtmlFile) throws IOException {
        try (PrintWriter writer = new PrintWriter(outputHtmlFile);
             FileReader reader = new FileReader(inputCsvFile)) {

            boolean commaLastSign = false;
            boolean newLineLastSign = false;
            boolean quotesLastSign = false;
            boolean quotesOpened = false;

            writer.print("<!DOCTYPE html><head><meta charset=\"UTF-8\"><body><table><tr><td>");

            int c;

            while ((c = reader.read()) != -1) {
                switch (c) {
                    case (34): //QUOTES
                        if ((commaLastSign || newLineLastSign) && !quotesOpened) {
                            quotesOpened = true;
                            break;
                        }

                        if (!quotesLastSign) {
                            quotesLastSign = true;
                            break;
                        }
                        writer.print((char) c);

                        quotesLastSign = false;

                        break;
                    case (44)://COMMA
                        if (quotesOpened && !quotesLastSign) {
                            writer.print((char) c);

                            break;
                        }
                        writer.print("</td><td>");

                        if (quotesLastSign) {
                            quotesOpened = false;
                            quotesLastSign = false;
                        }
                        commaLastSign = true;

                        break;
                    case (10): //NEW-LINE
                        if (!quotesLastSign & quotesOpened) {
                            writer.print("</br>");

                            break;
                        }
                        writer.print("</td></tr><tr><td>");
                        if (quotesLastSign) {
                            quotesOpened = false;
                            quotesLastSign = false;
                        }
                        newLineLastSign = true;

                        break;
                    case (60):
                        writer.print("&lt;");

                        break;
                    case (62):
                        writer.print("&gt;");

                        break;
                    case (38):
                        writer.print("&amp;");

                        break;
                    case (13):

                        break;
                    default:
                        commaLastSign = false;
                        newLineLastSign = false;
                        quotesLastSign = false;

                        writer.print((char) c);
                }
            }

            writer.print("</td></tr></table></body></head>");
        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
        }
    }

    public static void main(String[] args) throws IOException {
        String inputCsvFile = "FileForCsvTask.csv";
        String outputHtmlFile = "FileForCsvTask.html";
        convertToCsv(inputCsvFile, outputHtmlFile);
    }
}