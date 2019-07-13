package ru.academits.vasilev.vector;

import java.util.Arrays;

public class Vector {
    private int n;
    private double[] array;

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
        return this.n;
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

    public Vector multiply(double number) {
        for (int i = 0; i < this.array.length; i++) {
            this.array[i] *= number;
        }
        return new Vector(this.n, this.array);
    }

    public Vector revert() {
        for (int i = 0; i < this.array.length; i++) {
            this.array[i] *= -1;
        }
        return new Vector(this.n, this.array);
    }

    public double getLength() {
        double sum = 0;

        for (int i = 0; i < this.array.length; i++) {
            sum += this.array[i] * this.array[i];
        }
        return Math.sqrt(sum);
    }

    public Vector setElementByIndex(int index, double element) {
        if (index >= this.array.length) {
            throw new IllegalArgumentException();
        }
        this.array[index] = element;

        return new Vector(n, this.array);
    }

    public double getElementByIndex(int index) {
        if (index >= this.array.length) {
            throw new IllegalArgumentException();
        }

        return this.array[index];
    }

    public static Vector vectorsSum(Vector first, Vector second) {
        first.plusVector(second);
        return first;
    }

    public static Vector vectorsSubtraction(Vector first, Vector second) {
        first.minusVector(second);
        return first;
    }

    public static Vector vectorsMultiply(Vector first, Vector second) {
        if (first.array.length != second.array.length) {
            int tempLength = first.array.length < second.array.length ? second.array.length : first.array.length;
            double[] tempArray = first.array.length < second.array.length ? first.array : second.array;

            Vector tempVector = new Vector(tempLength, tempArray);

            for (int i = 0; i < tempVector.array.length; i++) {
                tempVector.array[i] *= second.array[i];
            }
            return tempVector;
        }
        for (int i = 0; i < first.array.length; i++) {
            first.array[i] *= second.array[i];
        }
        return new Vector(first.n, first.array);
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