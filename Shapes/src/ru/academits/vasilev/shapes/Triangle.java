package ru.academits.vasilev.shapes;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    private double ab;
    private double ac;
    private double bc;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;

        this.ab = getSideLength(x2, x1, y2, y1);
        this.ac = getSideLength(x3, x1, y3, y1);
        this.bc = getSideLength(x3, x2, y3, y2);
    }

    private static double getSideLength(double sideX1, double sideX2, double sideY1, double sideY2) {
        return Math.sqrt(Math.pow((sideX1 - sideX2), 2) + (Math.pow((sideY1 - sideY2), 2)));
    }

    private static double getMinimum(double a, double b, double c) {
        double min = a;
        if (min > b) {
            min = b;
        }
        if (min > c) {
            min = c;
        }
        return min;
    }

    private static double getMaximum(double a, double b, double c) {
        double max = a;
        if (max < b) {
            max = b;
        }
        if (max < c) {
            max = c;
        }
        return max;
    }

    @Override
    public double getWidth() {
        return getMinimum(x1, x2, x3) - getMaximum(x1, x2, x3);
    }

    @Override
    public double getHeight() {
        return getMinimum(y1, y2, y3) - getMaximum(y1, y2, y3);
    }

    @Override
    public double getArea() {
        double epsilon = 1.0e-10;

        if (Math.abs((x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1)) <= epsilon) {
            return 0;
        } else {
            double semiPerimeter = getPerimeter() / 2;

            return Math.sqrt(semiPerimeter * (semiPerimeter - ab) * (semiPerimeter - ac) * (semiPerimeter - bc));
        }
    }

    @Override
    public double getPerimeter() {
        return ab + ac + bc;
    }

    @Override
    public String toString() {
        return "triangle with vertex coordinates: a(" + x1 + ", " + y1 + "); b(" + x2 + ", " + y2 + "); c(" + x3 + ", " + y3 + ")";
    }

    @Override
    public boolean equals(Object triangle) {
        if (triangle == this) {
            return true;
        }
        if (triangle == null || triangle.getClass() != this.getClass()) {
            return false;
        }

        Triangle t = (Triangle) triangle;

        return x1 == t.x1 && y1 == t.y1 && x2 == t.x2 && y2 == t.y2 && x3 == t.x3 && y3 == t.y3;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);
        return hash;
    }
}