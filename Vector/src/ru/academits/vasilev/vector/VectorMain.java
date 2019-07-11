package ru.academits.vasilev.vector;

import java.util.Arrays;

public class VectorMain {
    public static void main(String[] args) {
        double[] array1 = {11, -1, 9, 34, 45};
        double[] array2 = {11, -1, 9, 34, 45, -90, 7};

        Vector vector1 = new Vector(7, array1);
        Vector vector2 = new Vector(7, array2);

        vector1.plusVector(vector2);

        System.out.println(vector1);

        vector1.minusVector(vector2);

        System.out.println(vector1);
    }
}