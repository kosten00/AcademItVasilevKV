package ru.academits.vasilev.vector;

import java.util.Arrays;

public class Vector {
    private double[] vectorArray;

    public Vector(int vectorLength) {
        if (vectorLength <= 0) {
            throw new IllegalArgumentException("Vector length can't be less than or equal to 0");
        }

        this.vectorArray = new double[vectorLength];
    }

    public Vector(Vector vector) {
        this.vectorArray = Arrays.copyOf(vector.vectorArray, vector.vectorArray.length);
    }

    public Vector(double[] array) {
        this.vectorArray = Arrays.copyOf(array, array.length);
    }

    public Vector(int vectorLength, double[] array) {
        if (array.length < vectorLength) {
            this.vectorArray = new double[vectorLength];

            for (int i = 0; i < vectorArray.length; i++) {
                if (i < array.length) {
                    vectorArray[i] = array[i];
                }
            }
        } else {
            this.vectorArray = Arrays.copyOf(array, array.length);
        }
    }

    public int getSize() {
        return this.vectorArray.length;
    }

    public void plusVector(Vector vector) {
        if (this.vectorArray.length < vector.vectorArray.length) {
            this.vectorArray = Arrays.copyOf(this.vectorArray, vector.vectorArray.length);
        }

        if (this.vectorArray.length > vector.vectorArray.length) {
            vectorArray = Arrays.copyOf(vector.vectorArray, this.vectorArray.length);
        }

        for (int i = 0; i < this.vectorArray.length; i++) {
            this.vectorArray[i] += vector.vectorArray[i];
        }
    }

    public void minusVector(Vector vector) {
        if (this.vectorArray.length < vector.vectorArray.length) {
            this.vectorArray = Arrays.copyOf(this.vectorArray, vector.vectorArray.length);
        }

        if (this.vectorArray.length > vector.vectorArray.length) {
            vectorArray = Arrays.copyOf(vector.vectorArray, this.vectorArray.length);
        }

        for (int i = 0; i < this.vectorArray.length; i++) {
            this.vectorArray[i] -= vector.vectorArray[i];
        }
    }

    public void multiply(double number) {
        for (int i = 0; i < this.vectorArray.length; i++) {
            this.vectorArray[i] *= number;
        }
    }

    public Vector revert() {
        for (int i = 0; i < this.vectorArray.length; i++) {
            this.vectorArray[i] *= -1;
        }
        return new Vector(this.vectorArray);
    }

    public double getLength() {
        double sum = 0;

        for (double e : this.vectorArray) {
            sum += e * e;
        }

        return Math.sqrt(sum);
    }

    public void setElementByIndex(int index, double element) {
        if (index >= this.vectorArray.length) {
            throw new ArrayIndexOutOfBoundsException("Index is greater than vector array boundaries!");
        }
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index can't be less than 0");
        }

        this.vectorArray[index] = element;
    }

    public double getElementByIndex(int index) {
        if (index >= this.vectorArray.length) {
            throw new ArrayIndexOutOfBoundsException("Index is greater than vector array boundaries!");
        }
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index can't be less than 0");
        }

        return this.vectorArray[index];
    }

    public static Vector getVectorsSum(Vector a, Vector b) {
        a.plusVector(b);
        return a;
    }

    public static Vector getVectorsSubtraction(Vector a, Vector b) {
        a.minusVector(b);
        return a;
    }

    public static double getVectorsMultiply(Vector a, Vector b) {
        if (a.vectorArray.length < b.vectorArray.length) {
            a.vectorArray = Arrays.copyOf(a.vectorArray, b.vectorArray.length);
        }

        if (a.vectorArray.length > b.vectorArray.length) {
            b.vectorArray = Arrays.copyOf(b.vectorArray, a.vectorArray.length);
        }

        double sum = 0;
        for (int i = 0; i < a.vectorArray.length; i++) {
            sum += a.vectorArray[i] * b.vectorArray[i];

        }
        return sum;
    }

    @Override
    public String toString() {
        return Arrays.toString(vectorArray);
    }

    @Override
    public boolean equals(Object vector) {
        if (vector == this) {
            return true;
        }
        if (vector == null || vector.getClass() != this.getClass()) {
            return false;
        }

        Vector v = (Vector) vector;

        return vector == v.vectorArray && vectorArray == v.vectorArray;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(vectorArray);
        return hash;
    }
}