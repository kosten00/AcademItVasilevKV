package ru.academits.vasilev.csv.Main;

import org.w3c.dom.ls.LSOutput;

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

    static void convertToCsv(String inputCsvFile, String outputHtmlFile) throws IOException {
        try (PrintWriter writer = new PrintWriter(outputHtmlFile);
             FileReader reader = new FileReader(inputCsvFile)) {

            int c;

            boolean commaLastCharacter = false;
            boolean lineSeparatorLastCharacter = false;
            boolean quotesInOpenedQuotesLastCharacter = false;
            boolean quotesOpened = false;

            boolean CR = false;

            while ((c = reader.read()) != -1) {
                switch (c) {
                    case 65279:
                        writer.print("<!DOCTYPE html><html><meta charset=\"UTF-8\"><body><head><table><tr><td>");
                        System.out.println("<!DOCTYPE html><html><meta charset=\"UTF-8\"><body><head><table><tr><td>");
                        writer.print((char) 65279);
                        break;
                    case QUOTES:
                        //System.out.println(" quotes");

                        if ((commaLastCharacter || lineSeparatorLastCharacter) && !quotesOpened) {
                            quotesOpened = true;
                            System.out.println("quotesOpened set true, check: " + quotesOpened);

                            break;
                        }

                        if (!quotesInOpenedQuotesLastCharacter) {
                            quotesInOpenedQuotesLastCharacter = true;
                            System.out.println("quotesInOpenedQuotesLastCharacter set true, check: " + quotesInOpenedQuotesLastCharacter);

                            break;
                        }
                        writer.print(QUOTES);
                        System.out.println(" printed quotes");

                        quotesInOpenedQuotesLastCharacter = false;
                        System.out.println("quotesInOpenedQuotesLastCharacter set false, check: " + quotesInOpenedQuotesLastCharacter);

                        break;
                    case COMMA:
                        System.out.println(" comma");
                        if (!quotesInOpenedQuotesLastCharacter & quotesOpened) {
                            writer.print(COMMA);
                            System.out.println("printed comma");

                            break;
                        }
                        writer.print("</td><td>");
                        System.out.println("</td><td>");

                        if (quotesInOpenedQuotesLastCharacter) {
                            quotesOpened = false;
                            System.out.println("quotesOpened set false" + quotesOpened);
                            quotesInOpenedQuotesLastCharacter = false;
                            System.out.println("quotesInOpenedQuotesLastCharacter set false, check: " + quotesInOpenedQuotesLastCharacter);
                        }
                        commaLastCharacter = true;
                        System.out.println("commaLastCharacter set true, check: " + commaLastCharacter);

                        break;
                    case NEXT_LINE:
                        System.out.println(" next line");

                        if (!quotesInOpenedQuotesLastCharacter & quotesOpened) {
                            writer.print("</br>");
                            System.out.println("</br>");
                            break;
                        }
                        writer.print("<tr><td>");
                        System.out.println("<tr><td>");

                        if (quotesInOpenedQuotesLastCharacter) {
                            quotesOpened = false;
                            quotesInOpenedQuotesLastCharacter = false;
                            System.out.println("quotesOpened set false, check " + quotesOpened + "quotesInOpenedQuotesLastCharacter set false, check: " + quotesInOpenedQuotesLastCharacter);
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
                        writer.print("</td></tr>");
                        System.out.println("</td></tr>");

                        System.out.println("CR");

                        CR = true;
                        break;
                    default:
                        if (CR && lineSeparatorLastCharacter) {

                        }

                        commaLastCharacter = false;
                        lineSeparatorLastCharacter = false;
                        quotesInOpenedQuotesLastCharacter = false;
                        CR = false;

                        writer.print((char) c);

                        System.out.print((char) c);
                }
            }
            writer.print("</td></tr></table></head></body></html>");
            System.out.println("</td></tr></table></head></body></html>");

        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
        }
    }
}
