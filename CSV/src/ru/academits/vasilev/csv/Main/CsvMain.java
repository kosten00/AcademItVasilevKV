package ru.academits.vasilev.csv.Main;

import java.io.*;

public class CsvMain {
    private static void convertToCsv(String inputCsvFile, String outputHtmlFile) throws IOException {
        try (PrintWriter writer = new PrintWriter(outputHtmlFile);
             FileReader reader = new FileReader(inputCsvFile)) {

            writer.print("<!DOCTYPE html><head><meta charset=\"UTF-8\"><body><table><tr><td>");

            int c;

            boolean commaLastCharacter = false;
            boolean lineSeparatorLastCharacter = false;
            boolean quotesInOpenQuotes = false;
            boolean quotesOpened = false;

            while ((c = reader.read()) != -1) {
                switch (c) {
                    case (34): //QUOTES
                        if ((commaLastCharacter || lineSeparatorLastCharacter) && !quotesOpened) {
                            quotesOpened = true;
                            break;
                        }

                        if (!quotesInOpenQuotes) {
                            quotesInOpenQuotes = true;
                            break;
                        }
                        writer.print((char) c);

                        quotesInOpenQuotes = false;

                        break;
                    case (44)://COMMA
                        if (!quotesInOpenQuotes & quotesOpened) {
                            writer.print((char) c);

                            break;
                        }
                        writer.print("</td><td>");

                        if (quotesInOpenQuotes) {
                            quotesOpened = false;
                            quotesInOpenQuotes = false;
                        }
                        commaLastCharacter = true;

                        break;
                    case (10): //NEW-LINE
                        if (!quotesInOpenQuotes & quotesOpened) {
                            writer.print("</br>");

                            break;
                        }
                        writer.print("</td></tr><tr><td>");

                        if (quotesInOpenQuotes) {
                            quotesOpened = false;
                            quotesInOpenQuotes = false;
                        }
                        lineSeparatorLastCharacter = true;

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
                        commaLastCharacter = false;
                        lineSeparatorLastCharacter = false;
                        quotesInOpenQuotes = false;

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