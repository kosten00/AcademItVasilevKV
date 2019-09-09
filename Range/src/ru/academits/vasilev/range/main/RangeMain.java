package ru.academits.vasilev.range.main;

import ru.academits.vasilev.range.Range;

public class RangeMain {
    public static void main(String[] args) {
        System.out.println("Range r1:");
        Range r1 = new Range(1, 10);
        r1.print();

        System.out.println("Ranges r2:");
        Range r2 = new Range(2, 6);
        r2.print();
        System.out.println();

        System.out.println("Range r3 is intersection of r1 and r2:");
        Range r3 = r1.getIntersection(r2);
        if (r3 == null) {
            System.out.println("Ranges do not intersect");
        } else {
            r3.print();
        }
        System.out.println();

        System.out.println("Array of ranges r5 is unity of r1 and r2:");
        Range[] r5 = r1.getUnion(r2);
        for (Range e : r5) {
            e.print();
        }
        System.out.println();

        System.out.println("Array of Ranges r4 is difference between r2 and r1:");
        Range[] r4 = r2.getDifference(r1);
        for (Range e : r4) {
            e.print();
        }
        System.out.println();
    }
}