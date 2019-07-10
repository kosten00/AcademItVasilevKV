package ru.academits.vasilev.shapes;

public class Square implements Shape {
    private double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getWidth() {
        return sideLength;
    }

    public double getHeight() {
        return sideLength;
    }

    public double getArea() {
        return sideLength * sideLength;
    }

    public double getPerimeter() {
        return sideLength * 4;
    }

    @Override
    public String toString() {
        return "Square with side length = " + sideLength;
    }

    @Override
    public boolean equals(Object square) {
        if (square == this) {
            return true;
        }
        if (square == null || square.getClass() != this.getClass()) {
            return false;
        }
        Square s = (Square) square;

        return sideLength == s.sideLength;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(sideLength);
        return hash;
    }
}