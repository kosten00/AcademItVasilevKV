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
        this.array = new double[n];
    }

    public Vector(Vector vector) {
        this.n = vector.n;
        this.array = vector.array;
    }

    public Vector(double[] array) {
        this.n = array.length;
        this.array = array;
    }

    public Vector(int n, double[] array) {
        this.n = n;

        if (array.length < n) {
            double[] tempArray = new double[n];

            for (int i = 0; i < tempArray.length; i++) {
                if (i < array.length) {
                    tempArray[i] = array[i];
                }
            }

            this.array = tempArray;
        } else {
            this.array = array;
        }
    }

    public int getSize() {
        return n;
    }

    public Vector plusVector(Vector vector) {
        if (this.array.length != vector.array.length) {
            int tempLength = this.array.length < vector.array.length ? vector.array.length : this.array.length;
            double[] tempArray = this.array.length < vector.array.length ? this.array : vector.array;

            Vector tempVector = new Vector(tempLength, tempArray);
            for (int i = 0; i < tempVector.array.length; i++) {
                tempVector.array[i] += vector.array[i];
            }
            return tempVector;
        }
        for (int i = 0; i < this.array.length; i++) {
            this.array[i] += vector.array[i];
        }
        return new Vector(this.n, this.array);
    }

    public Vector minusVector(Vector vector) {
        if (this.array.length != vector.array.length) {
            int tempLength = this.array.length < vector.array.length ? vector.array.length : this.array.length;
            double[] tempArray = this.array.length < vector.array.length ? this.array : vector.array;

            Vector tempVector = new Vector(tempLength, tempArray);
            for (int i = 0; i < tempVector.array.length; i++) {
                tempVector.array[i] -= vector.array[i];
            }
            return tempVector;
        }
        for (int i = 0; i < this.array.length; i++) {
            this.array[i] -= vector.array[i];
        }
        return new Vector(this.n, this.array);
    }


    @Override
    public String toString() {
        return Arrays.toString(array);
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

        return n == v.n && array == v.array;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + n;
        hash = prime * hash + Arrays.hashCode(array);
        return hash;
    }
}