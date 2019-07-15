package ru.academits.vasilev.range;

public class RangeMain {
    public static void main(String[] args) {
        System.out.println("Two ranges, that do not intersect: ");

        Range range1 = new Range(1, 2);
        range1.print();
        System.out.println("First range have length of " + range1.getLength());

        Range range2 = new Range(3, 4);
        range2.print();
        System.out.println("Second range have length of " + range2.getLength());

        System.out.println("Check if x = 1 is inside of ranges: ");
        double x = 1;

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

        System.out.println("Ranges unite: ");
        for (Range e : range1.uniteRanges(range2)) {
            e.print();
        }

        System.out.println("Ranges difference: ");
        for (Range e : range1.getRangesDifference(range2)) {
            e.print();
        }

        System.out.println();

        System.out.println("Two ranges, that do intersect, but don't overlap each other: ");

        Range range3 = new Range(1, 3);
        range3.print();
        System.out.println("First range have length of " + range3.getLength());

        Range range4 = new Range(2, 4);
        range4.print();
        System.out.println("Second range have length of " + range4.getLength());

        if (range3.getRangesIntersection(range4) == null) {
            System.out.println("Ranges don't intersect");
        } else {
            System.out.print("Ranges intersect in ");
            range3.getRangesIntersection(range4).print();
        }

        System.out.println("Ranges unite: ");
        for (Range e : range3.uniteRanges(range4)) {
            e.print();
        }

        System.out.println("Ranges difference: ");
        for (Range e : range3.getRangesDifference(range4)) {
            e.print();
        }

        System.out.println();

        System.out.println("Overlapping ranges: ");

        Range range5 = new Range(1, 6);
        range5.print();
        System.out.println("First range have length of " + range5.getLength());

        Range range6 = new Range(2, 4);
        range6.print();
        System.out.println("Second range have length of " + range6.getLength());

        if (range5.getRangesIntersection(range6) == null) {
            System.out.println("Ranges don't intersect");
        } else {
            System.out.print("Ranges intersect in ");
            range5.getRangesIntersection(range6).print();
        }

        System.out.println("Ranges unite: ");
        for (Range e : range5.uniteRanges(range6)) {
            e.print();
        }

        System.out.println("Ranges difference: ");
        for (Range e : range5.getRangesDifference(range6)) {
            e.print();
        }

        System.out.println();

        System.out.println("Now same ranges transmitted to methods in reverse order: ");

        System.out.println("Two ranges, that do not intersect: ");

        range2.print();
        System.out.println("First range have length of " + range2.getLength());

        range1.print();
        System.out.println("Second range have length of " + range1.getLength());

        if (range2.getRangesIntersection(range1) == null) {
            System.out.println("Ranges don't intersect");
        } else {
            System.out.print("Ranges intersect in ");
            range2.getRangesIntersection(range1).print();
        }

        System.out.println("Ranges unite: ");
        for (Range e : range2.uniteRanges(range1)) {
            e.print();
        }

        System.out.println("Ranges difference: ");
        for (Range e : range2.getRangesDifference(range1)) {
            e.print();
        }

        System.out.println();

        System.out.println("Two ranges, that do intersect, but don't overlap each other: ");

        range4.print();
        System.out.println("First range have length of " + range3.getLength());

        range3.print();
        System.out.println("Second range have length of " + range3.getLength());

        if (range4.getRangesIntersection(range3) == null) {
            System.out.println("Ranges don't intersect");
        } else {
            System.out.print("Ranges intersect in ");
            range4.getRangesIntersection(range3).print();
        }

        System.out.println("Ranges unite: ");
        for (Range e : range4.uniteRanges(range3)) {
            e.print();
        }

        System.out.println("Ranges difference: ");
        for (Range e : range4.getRangesDifference(range3)) {
            e.print();
        }

        System.out.println();

        System.out.println("Overlapping ranges: ");

        range6.print();
        System.out.println("First range have length of " + range6.getLength());

        range5.print();
        System.out.println("Second range have length of " + range5.getLength());

        if (range6.getRangesIntersection(range5) == null) {
            System.out.println("Ranges don't intersect");
        } else {
            System.out.print("Ranges intersect in ");
            range6.getRangesIntersection(range5).print();
        }

        System.out.println("Ranges unite: ");
        for (Range e : range6.uniteRanges(range5)) {
            e.print();
        }

        System.out.println("Ranges difference: ");
        for (Range e : range6.getRangesDifference(range5)) {
            e.print();
        }

        System.out.println();

        System.out.println("Rages with equal ends: ");

        Range range7 = new Range(3, 8);
        range7.print();
        System.out.println("First range have length of " + range7.getLength());

        Range range8 = new Range(8, 10);
        range8.print();
        System.out.println("Second range have length of " + range8.getLength());

        if (range7.getRangesIntersection(range8) == null) {
            System.out.println("Ranges don't intersect");
        } else {
            System.out.print("Ranges intersect in ");
            range7.getRangesIntersection(range8).print();
        }

        System.out.println("Ranges unite: ");
        for (Range e : range7.uniteRanges(range8)) {
            e.print();
        }

        System.out.println("Ranges difference: ");
        for (Range e : range7.getRangesDifference(range8)) {
            e.print();
        }
    }
}