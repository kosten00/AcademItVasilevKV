package ru.academits.vasilev.range;

import java.util.Scanner;

public class RangeMain {
    public static double getMin(double a, double b) {
        if (a < b) {
            return a;
        }
        return b;
    }

    public static double getMax(double a, double b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    public static Range getNewRange() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input new range name: ");
        String rangeName = scanner.nextLine();

        System.out.println("Input begin limit in double number: ");
        double rangeLimit01 = scanner.nextDouble();

        System.out.println("Input end limit in double number");
        double rangeLimit02 = scanner.nextDouble();

        return new Range(rangeName, getMin(rangeLimit01, rangeLimit02), getMax(rangeLimit01, rangeLimit02));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Range range1 = getNewRange();
        range1.print();
        System.out.println("Range named " + range1.showName() + " has length of " + range1.getLength());

        Range range2 = getNewRange();
        range2.print();
        System.out.println("Range named " + range2.showName() + " has length of " + range2.getLength());

        System.out.println("Input double number to check if it is inside of ranges: ");

        double x = scanner.nextDouble();

        if (!range1.isInside(x) && !range2.isInside(x)) {
            System.out.println("Number is outside of both ranges");
        } else if (!range1.isInside(x) && range2.isInside(x)) {
            System.out.println("Number is inside range " + range2.showName());
        } else if (range1.isInside(x) && !range2.isInside(x)) {
            System.out.println("Number is inside range " + range1.showName());
        } else {
            System.out.println("Number is inside of both ranges");
        }
    }
}