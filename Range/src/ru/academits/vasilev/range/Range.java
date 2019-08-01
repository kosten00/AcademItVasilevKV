package ru.academits.vasilev.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public void print() {
        System.out.printf("range from %s, to %s ", from, to);
        System.out.println();
    }

    public boolean isInside(double x) {
        return from <= x && to >= x;
    }

    public static Range getIntersection(Range r1, Range r2) {
        if (Math.min(r1.to, r2.to) < Math.max(r1.from, r2.from)) {
            return null;
        }
        return new Range(Math.max(r1.from, r2.from), Math.min(r1.to, r2.to));
    }

    public static Range[] getUnite(Range r1, Range r2) {
        if (Math.min(r1.to, r2.to) < Math.max(r1.from, r2.from)) {
            return new Range[]{r1, r2};
        }
        return new Range[]{new Range(Math.min(r1.from, r2.from), Math.max(r1.to, r2.to)),};
    }

    public static Range[] getDifference(Range r1, Range r2) {
        if (Math.min(r1.to, r2.to) < Math.max(r1.from, r2.from)) {
            return new Range[]{r1, r2};
        }
        if (r1.from < r2.from && r1.to > r2.to) {
            return new Range[]{new Range(Math.min(r1.from, r2.from), Math.max(r1.from, r2.from)),
                    new Range(Math.min(r1.to, r2.to), Math.max(r1.to, r2.to))};
        }
        if (r1.to > r2.to) {
            return new Range[]{new Range(r2.to, r1.to),};
        }
        if (r1.from < r2.from) {
            return new Range[]{new Range(r1.from, r2.from)};
        }
        return new Range[]{new Range(0, 0), new Range(0, 0)};
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getFrom() {
        return from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getTo() {
        return to;
    }
}