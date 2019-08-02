package ru.academits.vasilev.matrix;

import ru.academits.vasilev.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] column;
    private Vector raw;

    public Matrix(int n, int m) {
        column = new Vector[n];
        raw = new Vector(m);

        for (int i = 0; i < n; i++) {
            column[i] = new Vector(m);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(column);
    }
}


