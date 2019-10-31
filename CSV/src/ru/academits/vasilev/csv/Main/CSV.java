package ru.academits.vasilev.csv.Main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

class CSV {
    private static final char QUOTES = 34;
    private static final char COMMA = 44;
    private static final char NEXT_LINE = 10;
    private static final char CARRIAGE_RETURN = 13;
    private static final char LESS_SIGN = 60;
    private static final char GREATER_SIGN = 62;
    private static final char AMP_SIGN = 38;
    private static final char EMPTY_LINE = 0;

    static void convertToCsv(String inputCsvFile, String outputHtmlFile) throws IOException {
        try (PrintWriter writer = new PrintWriter(outputHtmlFile);
             FileReader reader = new FileReader(inputCsvFile)) {

            writer.print("<!DOCTYPE html><html><meta charset=\"UTF-8\"><body><head><table><tr><td>");

            int c;

            //change int to char, make writer to print constants

            boolean commaLastCharacter = false;
            boolean lineSeparatorLastCharacter = false;
            boolean quotesInOpenedQuotesLastCharacter = false;
            boolean quotesOpened = false;


            while ((c = reader.read()) != -1) {
                switch (c) {
                    case QUOTES:
                        if ((commaLastCharacter || lineSeparatorLastCharacter) && !quotesOpened) {
                            quotesOpened = true;
                            break;
                        }

                        if (!quotesInOpenedQuotesLastCharacter) {
                            quotesInOpenedQuotesLastCharacter = true;
                            break;
                        }
                        writer.print(QUOTES);

                        quotesInOpenedQuotesLastCharacter = false;

                        break;
                    case COMMA:
                        if (!quotesInOpenedQuotesLastCharacter & quotesOpened) {
                            writer.print(COMMA);

                            break;
                        }
                        writer.print("</td><td>");

                        if (quotesInOpenedQuotesLastCharacter) {
                            quotesOpened = false;
                            quotesInOpenedQuotesLastCharacter = false;
                        }
                        commaLastCharacter = true;

                        break;
                    case NEXT_LINE:
                        System.out.println();

                        if (!quotesInOpenedQuotesLastCharacter & quotesOpened) {
                            writer.print("</br>");

                            break;
                        }
                        writer.print("</td></tr><tr><td>");

                        if (quotesInOpenedQuotesLastCharacter) {
                            quotesOpened = false;
                            quotesInOpenedQuotesLastCharacter = false;
                        }
                        lineSeparatorLastCharacter = true;

                        break;
                    case LESS_SIGN:
                        writer.print("&lt;");

                        break;
                    case GREATER_SIGN:
                        writer.print("&gt;");

                        break;
                    case AMP_SIGN:
                        writer.print("&amp;");

                        break;
                    case CARRIAGE_RETURN:

                        break;
//                    case EMPTY_LINE:
//                        if (lineSeparatorLastCharacter){
//                            System.out.println("line sep last char, from case empty line");
//                        }
//
//                        break;
                    default:
                        commaLastCharacter = false;
                        lineSeparatorLastCharacter = false;
                        quotesInOpenedQuotesLastCharacter = false;

                        writer.print((char) c);
                }
            }
            writer.print("</td></tr></table></head></body></html>");
        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
        }
    }
}
