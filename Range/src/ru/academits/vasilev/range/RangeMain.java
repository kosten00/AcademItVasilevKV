package ru.academits.vasilev.range;

import java.util.Scanner;

public class RangeMain {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Range range1 = new Range(1, 5);
        range1.print();
        System.out.println("First range have length of " + range1.getLength());

        Range range2 = new Range(5, 7);
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


        if (range1.getRangesIntersection(range2) == null) {
            System.out.println("Ranges don't intersect");
        } else {
            System.out.print("Ranges intersect in ");
            range1.getRangesIntersection(range2).print();
        }

        System.out.println("Ranges sum: ");
        for (Range e : range1.uniteRanges(range2)) {
            e.print();
        }

        System.out.println("Ranges quotient: ");
        for (Range e : range1.getRangesDifference(range2)) {
            e.print();
        }
    }
}