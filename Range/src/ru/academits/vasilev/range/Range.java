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

    public Range getRangesIntersection(Range first, Range second) {
        if (first.to <= second.from) {
            return null;
        } else {
            double IntersectionRangeFrom = first.from > second.from ? first.from : second.from;
            double IntersectionRangeTo = first.to < second.to ? first.to : second.to;
            return new Range(IntersectionRangeFrom, IntersectionRangeTo);
        }
    }

    public Range getRangesSum(Range first, Range second) {
        if (first.to < second.from) {

        }
        return new Range(first.from, second.to);
    }

    public void getRangesQuotient(Range first, Range second) {

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