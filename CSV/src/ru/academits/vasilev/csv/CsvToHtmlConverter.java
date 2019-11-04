package ru.academits.vasilev.csv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CsvToHtmlConverter {
    private static final char COMMA = 44;
    private static final char QUOTES = 34;
    private static final char NEXT_LINE = 10;
    private static final char CARRIAGE_RETURN = 13;
    private static final char LESS_SIGN = 60;
    private static final char GREATER_SIGN = 62;
    private static final char AMP_SIGN = 38;

    private boolean commaLastCharacter = false;
    private boolean quotesOpened = false;
    private boolean quotesInOpenedQuotesLastCharacter = false;
    private boolean lineSeparatorLastCharacter = false;
    private boolean carriageReturnLastCharacter = false;
    private boolean lineSeparatorAfterCarriageReturnLastCharacter = false;

    public void convertToCsv(String inputCsvFile, String outputHtmlFile) throws IOException {
        try (PrintWriter writer = new PrintWriter(outputHtmlFile);
             FileReader reader = new FileReader(inputCsvFile)) {

            writer.print("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>CsvInHtml</title></head><body><table><tr><td>");

            int c;

            while ((c = reader.read()) != -1) {
                switch (c) {
                    case COMMA:
                        if (lineSeparatorAfterCarriageReturnLastCharacter) {
                            writer.print("</td></tr><tr><td>");
                            lineSeparatorAfterCarriageReturnLastCharacter = false;
                        }

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
                    case QUOTES:
                        if (lineSeparatorAfterCarriageReturnLastCharacter) {
                            writer.print("</td></tr><tr><td>");
                            lineSeparatorAfterCarriageReturnLastCharacter = false;
                        }

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
                    case NEXT_LINE:
                        if (carriageReturnLastCharacter) {
                            carriageReturnLastCharacter = false;
                            lineSeparatorLastCharacter = false;
                            lineSeparatorAfterCarriageReturnLastCharacter = true;
                        }

                        if (!quotesInOpenedQuotesLastCharacter & quotesOpened) {
                            writer.print("</br>");
                            lineSeparatorAfterCarriageReturnLastCharacter = false;

                            break;
                        }

                        if (quotesInOpenedQuotesLastCharacter) {
                            quotesOpened = false;
                            quotesInOpenedQuotesLastCharacter = false;
                        }
                        lineSeparatorLastCharacter = true;

                        break;
                    case CARRIAGE_RETURN:
                        carriageReturnLastCharacter = true;

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
                    default:
                        if (lineSeparatorAfterCarriageReturnLastCharacter) {
                            writer.print("</td></tr><tr><td>");
                            lineSeparatorAfterCarriageReturnLastCharacter = false;
                        }
                        commaLastCharacter = false;
                        lineSeparatorLastCharacter = false;
                        quotesInOpenedQuotesLastCharacter = false;
                        carriageReturnLastCharacter = false;

                        writer.print((char) c);
                }
            }
            writer.print("</td></tr></table></body></html>");
        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
        }
    }
}