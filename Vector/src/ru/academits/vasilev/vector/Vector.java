package ru.academits.vasilev.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int vectorLength) {
        if (vectorLength <= 0) {
            throw new IllegalArgumentException("Vector length can't be less than or equal to 0");
        }

        components = new double[vectorLength];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Vector length can't be less than or equal to 0");
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int vectorLength, double[] array) {
        if (vectorLength <= 0) {
            throw new IllegalArgumentException("Vector length can't be less than or equal to 0");
        }

        components = Arrays.copyOf(array, vectorLength);
    }

    public int getSize() {
        return components.length;
    }

    public void plusVector(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < components.length; i++) {
            if (i >= vector.components.length) {
                continue;
            }

            components[i] += vector.components[i];
        }
    }

    public void minusVector(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < components.length; i++) {
            if (i >= vector.components.length) {
                continue;
            }

            components[i] -= vector.components[i];
        }
    }

    public void multiply(double number) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= number;
        }
    }

    public void revert() {
        this.multiply(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double e : components) {
            sum += e * e;
        }

        return Math.sqrt(sum);
    }

    public void setElement(int index, double element) {
        if (index >= components.length) {
            throw new IndexOutOfBoundsException("Index is greater than contents boundaries!");
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Index can't be less than 0");
        }

        components[index] = element;
    }

    public double getElement(int index) {
        if (index >= components.length) {
            throw new IndexOutOfBoundsException("Index is greater than contents boundaries!");
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Index can't be less than 0");
        }

        return components[index];
    }

    public static Vector getVectorsSum(Vector a, Vector b) {
        Vector sum = new Vector(a);

        sum.plusVector(b);

        return sum;
    }

    public static Vector getVectorsSubtraction(Vector a, Vector b) {
        Vector subtraction = new Vector(a);

        subtraction.plusVector(b);

        return subtraction;
    }

    public static double getVectorsMultiply(Vector a, Vector b) {
        double sum = 0;

        for (int i = 0; i < Math.min(a.components.length, b.components.length); i++) {
            sum += a.components[i] * b.components[i];
        }

        return sum;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < components.length; i++) {
            if (i == 0) {
                stringBuilder.append("{ ").append(components[i]).append(", ");
                continue;
            }

            if (i == components.length - 1) {
                stringBuilder.append(components[i]).append(" }");
                continue;
            }

            stringBuilder.append(components[i]).append(", ");
        }

        return stringBuilder.toString();
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

        return Arrays.equals(components, v.components);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(components);

        return hash;
    }
}