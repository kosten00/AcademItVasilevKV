package ru.academits.vasilev.range;

public class Range {
    private String name;
    private double from;
    private double to;

    public Range(String name, double from, double to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    public String showName() {
        return name;
    }

    public double getLength() {
        return to - from;
    }

    public void print() {
        System.out.printf("Range named %s created, from %s, to %s ", name, from, to);
        System.out.println();
    }

    public boolean isInside(double x) {
        return from <= x && to >= x;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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