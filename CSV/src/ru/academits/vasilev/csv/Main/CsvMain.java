package ru.academits.vasilev.csv.Main;

import java.io.*;

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

            writer.print("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><body><table><tr><td>");

            int c;

            boolean commaLastCharacter = false;
            boolean lineSeparatorLastCharacter = false;
            boolean quotesInOpenedQuotesLastCharacter = false;
            boolean quotesOpened = false;

            while ((c = reader.read()) != -1) {
                switch ((char) c) {
                    case QUOTES:
                        if ((commaLastCharacter || lineSeparatorLastCharacter) && !quotesOpened) {
                            quotesOpened = true;
                            break;
                        }

                        if (!quotesInOpenedQuotesLastCharacter) {
                            quotesInOpenedQuotesLastCharacter = true;
                            break;
                        }
                        writer.print((char) c);

                        quotesInOpenedQuotesLastCharacter = false;

                        break;
                    case COMMA:
                        if (!quotesInOpenedQuotesLastCharacter & quotesOpened) {
                            writer.print((char) c);

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
                    case EMPTY_LINE:

                        break;
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

public class CsvMain {
    public static void main(String[] args) {
        if (args.length == 2) {
            try {
                CSV.convertToCsv(args[0], args[1]);
            } catch (IOException ignored) {
                System.out.println("Read/write error");
            }
        } else {
            System.out.println("Input and output file's names must be arguments of the program.");
        }
    }
}