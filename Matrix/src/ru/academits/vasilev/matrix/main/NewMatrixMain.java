package ru.academits.vasilev.matrix.main;

import ru.academits.vasilev.matrix.Matrix;
import ru.academits.vasilev.vector.Vector;

public class NewMatrixMain {
    public static void main(String[] args) {
        double[] arr1 = {12, 5, 48};
        double[] arr2 = {43, 5, 7};
        double[] arr3 = {11, 23};
        Vector[] v1 = {new Vector(arr1), new Vector(arr2), new Vector(arr3)};
        Vector[] v2 = {new Vector(arr3), new Vector(arr2), new Vector(arr1)};

        Matrix m1 = new Matrix(v1);
        Matrix m2 = new Matrix(v2);

        System.out.println(m1);
        System.out.println(m2);

        System.out.println(Matrix.multiply(m1, m2));
    }
}