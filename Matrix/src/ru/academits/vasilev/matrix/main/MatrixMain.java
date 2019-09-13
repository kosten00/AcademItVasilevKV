package ru.academits.vasilev.matrix.main;

import ru.academits.vasilev.vector.Vector;
import ru.academits.vasilev.matrix.Matrix;

import java.util.Arrays;

public class MatrixMain {
    public static void main(String[] args) {
        Matrix m1 = new Matrix(5, 3);
        System.out.println("matrix with 0: " + m1);

        double[][] array = {{4, 56, 8}, {1, 9, 67}, {0, 3}};
        Matrix m2 = new Matrix(array);
        System.out.println(m2);

        Matrix m3 = new Matrix(m2);
        System.out.println(m3);

        double[] arr1 = {12, 5, 48, 0, 0};
        double[] arr2 = {43, 5, 7};
        double[] arr3 = {11, 23};
        Vector[] vectorsArr = {new Vector(arr1), new Vector(arr2), new Vector(arr3)};
        Matrix m4 = new Matrix(vectorsArr);
        System.out.println("matrix m4: " + m4);

        System.out.println("matrix m4 have size of " + Arrays.toString(m4.getSize()));

        System.out.println("return column from matrix m4 with index 2: " + m4.getColumn(2));

        System.out.println("return raw from matrix m4 with index 0: " + m4.getRaw(0));

        System.out.println("before setRaw: " + m2);
        Vector v1 = new Vector(arr2);
        System.out.println("raw to set: " + v1);
        m2.setRaw(1, v1);
        System.out.println("after setRaw: " + m2);

        m2.transpose();
        System.out.println("transpose matrix m2: " + m2);

        m2.multiplyByScalar(2);
        System.out.println("matrix m2 multiplied by 2: " + m2);

        double[][] arr4 = new double[][]{{2, 3, 4}, {8, 7, 6}, {9, 1, 0}};

        Matrix matrixArr4 = new Matrix(arr4);

        System.out.println(matrixArr4.getDeterminant());

        double[][] test = {{2, -5, 8}, {-8, 9, 1}, {0, 2, -3}};

        Matrix m9 = new Matrix(test);

        System.out.println(m9.getDeterminant());

        Vector v = new Vector(arr2);

        System.out.println(m2);

        m2.multiply(v);

        System.out.println(m2);
    }
}