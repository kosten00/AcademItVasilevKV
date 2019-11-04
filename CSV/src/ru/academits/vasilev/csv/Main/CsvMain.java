package ru.academits.vasilev.csv.Main;

import ru.academits.vasilev.csv.CsvToHtmlConverter;

import java.io.*;

public class CsvMain {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Input and output file's names must be arguments of the program.");
        } else {
            try {
                CsvToHtmlConverter converter = new CsvToHtmlConverter();
                converter.convertToCsv(args[0], args[1]);
            } catch (IOException ignored) {
                System.out.println("Read/write error");
            }
        }
    }
}