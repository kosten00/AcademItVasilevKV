package ru.academits.vasilev.vector;

import java.util.Arrays;

public class Vector {
    private double[] contents;

    public Vector(int vectorLength) {
        if (vectorLength <= 0) {
            throw new IllegalArgumentException("Vector length can't be less than or equal to 0");
        } else {
            this.contents = new double[vectorLength];
        }
    }

    public Vector(Vector vector) {
        this.contents = Arrays.copyOf(vector.contents, vector.contents.length);
    }

    public Vector(double[] array) {
        this.contents = Arrays.copyOf(array, array.length);
    }

    public Vector(int vectorLength, double[] array) {
        if (vectorLength <= 0) {
            throw new IllegalArgumentException("Vector length can't be less than or equal to 0");
        } else {
            if (vectorLength == array.length) {
                this.contents = Arrays.copyOf(array, array.length);
            } else {
                this.contents = new double[vectorLength];

                for (int i = 0; i < this.contents.length; i++) {
                    if (i >= array.length) {
                        this.contents[i] = 0;
                    } else {
                        this.contents[i] = array[i];
                    }
                }
            }
        }
    }

    public int getSize() {
        return this.contents.length;
    }

    public void plusVector(Vector vector) {
        if (this.contents.length < vector.contents.length) {
            this.contents = Arrays.copyOf(this.contents, vector.contents.length);
        }

        if (this.contents.length > vector.contents.length) {
            this.contents = Arrays.copyOf(vector.contents, this.contents.length);
        }

        for (int i = 0; i < this.contents.length; i++) {
            this.contents[i] += vector.contents[i];
        }
    }

    public void minusVector(Vector vector) {
        if (this.contents.length < vector.contents.length) {
            this.contents = Arrays.copyOf(this.contents, vector.contents.length);
        }

        if (this.contents.length > vector.contents.length) {
            this.contents = Arrays.copyOf(vector.contents, this.contents.length);
        }

        for (int i = 0; i < this.contents.length; i++) {
            this.contents[i] -= vector.contents[i];
        }
    }

    public void multiply(double number) {
        for (int i = 0; i < this.contents.length; i++) {
            this.contents[i] *= number;
        }
    }

    public void revert() {
        for (int i = 0; i < this.contents.length; i++) {
            this.contents[i] *= -1;
        }
    }

    public double getLength() {
        double sum = 0;

        for (double e : this.contents) {
            sum += e * e;
        }

        return Math.sqrt(sum);
    }

    public void setElementByIndex(int index, double element) {
        if (index >= this.contents.length) {
            throw new IndexOutOfBoundsException("Index is greater than contents boundaries!");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index can't be less than 0");
        }

        this.contents[index] = element;
    }

    public double getElementByIndex(int index) {
        if (index >= this.contents.length) {
            throw new IndexOutOfBoundsException("Index is greater than contents boundaries!");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index can't be less than 0");
        }

        return this.contents[index];
    }

    private static int getMaxVectorLength(Vector a, Vector b) {
        int max = a.contents.length;
        if (max < b.contents.length) {
            max = b.contents.length;
        }
        return max;
    }

    public static Vector getVectorsSum(Vector a, Vector b) {
        int maxVectorLength = getMaxVectorLength(a, b);

        Vector sum = new Vector(maxVectorLength, a.contents);

        for (int i = 0; i < sum.contents.length; i++) {
            if (i >= b.contents.length) {
                continue;
            }
            sum.contents[i] += b.contents[i];
        }
        return sum;
    }

    public static Vector getVectorsSubtraction(Vector a, Vector b) {
        int maxVectorLength = getMaxVectorLength(a, b);

        Vector subtraction = new Vector(maxVectorLength, a.contents);

        for (int i = 0; i < maxVectorLength; i++) {
            if (i >= b.contents.length) {
                continue;
            }
            subtraction.contents[i] -= b.contents[i];
        }
        return subtraction;
    }

    public static double getVectorsMultiply(Vector a, Vector b) {
        int maxVectorLength = getMaxVectorLength(a, b);
        double sum = 0;

        for (int i = 0; i < maxVectorLength; i++) {
            if (i >= b.contents.length || i >= a.contents.length) {
                continue;
            }
            sum += a.contents[i] * b.contents[i];
        }
        return sum;
    }

    @Override
    public String toString() {
        return Arrays.toString(contents);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Vector v = (Vector) o;

        return contents == v.contents;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(contents);
        return hash;
    }
}