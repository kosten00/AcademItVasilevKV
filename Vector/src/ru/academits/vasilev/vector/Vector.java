package ru.academits.vasilev.vector;

import java.util.Arrays;

public class Vector {
    private int n;
    private double[] array;
    private Vector vector;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        double[] array = new double[n];
    }

    public Vector(Vector vector) {
        n = vector.n;
        array = vector.array;
    }

    public Vector(double[] array) {
        this.array = array;
    }

    public Vector(int n, double[] array) {
        this.n = n;
        this.array = array;

        if (array.length < n) {
            for (int i = array.length - 1; i < n; i++) {
                i = 0;
            }
        }
    }

    public int getSize() {
        return n;
    }

    @Override
    public String toString() {
        return "Square with side length = " + Arrays.toString(array);
    }


}
