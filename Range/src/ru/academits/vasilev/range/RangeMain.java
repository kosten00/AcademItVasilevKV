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

    public static Range[] getRangesArray(Range first, Range second) {
        Range[] rangeArray = {first, second};
        return rangeArray;
    }

    public static Range getRangeFromTo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input double number: ");
        double from = scanner.nextDouble();

        System.out.println("Input double number");
        double to = scanner.nextDouble();

        return new Range(getMin(from, to), getMax(from, to));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Range range1 = getRangeFromTo();
        range1.print();
        System.out.println("First range have length of " + range1.getLength());

        Range range2 = getRangeFromTo();
        range2.print();
        System.out.println("Second range have length of " + range2.getLength());

        System.out.println("Input double number to check if it is inside of ranges: ");

        double x = scanner.nextDouble();

        if (!range1.isInside(x) && !range2.isInside(x)) {
            System.out.println("Number is outside of both ranges");
        } else if (!range1.isInside(x) && range2.isInside(x)) {
            System.out.println("Number is inside second range ");
        } else if (range1.isInside(x) && !range2.isInside(x)) {
            System.out.println("Number is inside first range ");
        } else {
            System.out.println("Number is inside both ranges");
        }

        Range emptyRange = new Range(-1, -1);
        boolean intersects = false;

        if (emptyRange.getRangesIntersection(range1, range2) == null) {
            System.out.print("Ranges are not crossing");
        } else {
            System.out.print("Ranges are crossing in ");
            emptyRange.getRangesIntersection(range1, range2).print();
            intersects = true;
        }

        if (intersects) {
            System.out.println("First and second ranges sum is ");
            emptyRange.getRangesSum(range1, range2).print();
        } else {
            getRangesArray(range1, range2);
        }

    }
}