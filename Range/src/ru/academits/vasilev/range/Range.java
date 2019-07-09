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

    public Range getRangesIntersection(double from, double to) {
        if (this.to <= from) {
            return null;
        } else {
            return new Range(this.from > from ? this.from : from, to < this.to ? to : this.to);
        }
    }

    public Range[] getRangesSum(double from, double to) {
        if (this.to < from) {
            return new Range[]{new Range(this.from, this.to), new Range(from, to)};
        }
        return new Range[]{new Range(this.from, to)};
    }

    public Range[] getRangesQuotient(double from, double to) {
        if (new Range(this.from, this.to).getRangesIntersection(from, to) != null) {
            return new Range[]{new Range(this.from, from)};
        } else {
            return new Range[]{new Range(this.from, this.to), new Range(from, to)};
        }
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