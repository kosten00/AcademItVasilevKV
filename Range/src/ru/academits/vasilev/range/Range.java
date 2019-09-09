package ru.academits.vasilev.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
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
        return x >= from && x <= to;
    }

    public Range getIntersection(Range range) {
        if (Math.min(to, range.to) <= Math.max(from, range.from)) {
            return null;
        }

        return new Range(Math.max(from, range.from), Math.min(to, range.to));
    }

    public Range[] getUnion(Range range) {
        if (Math.min(to, range.to) < Math.max(from, range.from)) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to)),};
    }

    public Range[] getDifference(Range range) {
        if (Math.min(to, range.to) <= Math.max(from, range.from)) {
            return new Range[]{new Range(from, to),};
        }

        if (from <= range.from && to >= range.to) {
            return new Range[]{new Range(Math.min(from, range.from), Math.max(from, range.from)),
                    new Range(Math.min(to, range.to), Math.max(to, range.to))};
        }

        if (to >= range.to) {
            return new Range[]{new Range(range.to, to),};
        }

        if (from <= range.from) {
            return new Range[]{new Range(from, range.from)};
        }

        return new Range[]{};
    }
}