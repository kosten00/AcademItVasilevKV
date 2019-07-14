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

    public Range getRangesIntersection(Range range) {
        if (range.from < this.from && this.to < range.to) {
            return new Range(this.from, this.to);
        }

        if (this.from < range.from && this.to > range.to) {
            return new Range(range.from, range.to);
        } else {

            if (this.to >= range.from) {
                return new Range(range.from, this.to);
            }

            if (range.to >= this.from) {
                return new Range(this.from, range.to);
            }
        }
        return null;
    }


    public Range[] uniteRanges(Range range) {
        if (this.to < from) {
            return new Range[]{new Range(this.from, this.to), new Range(from, to)};
        }
        return new Range[]{new Range(this.from, to)};
    }

    public Range[] getRangesDifference(Range range) {
        if (this.getRangesIntersection(range) == null) {
            return new Range[]{this, range};
        }

        if (range.from < this.from && range.to > this.to) {
            return new Range[]{new Range(range.from, this.from), new Range(this.to, range.to)};
        }

        if (this.from < range.from && this.to > range.to) {
            return new Range[]{new Range(this.from, range.from), new Range(range.to, this.to)};
        }

        if (this.from < range.from) {
            return new Range[]{new Range(this.from, range.from)};
        }

        if (range.from < this.from) {
            return new Range[]{new Range(range.from, this.from)};
        }

        return new Range[]{this};

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