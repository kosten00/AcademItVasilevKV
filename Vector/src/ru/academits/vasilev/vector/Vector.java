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

    public void sum(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(double number) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= number;
        }
    }

    public void revert() {
        this.multiplyByScalar(-1);
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

        sum.sum(b);

        return sum;
    }

    public static Vector getVectorsSubtraction(Vector a, Vector b) {
        Vector subtraction = new Vector(a);

        subtraction.subtract(b);

        return subtraction;
    }

    public static double getScalarMultiplication(Vector a, Vector b) {
        double sum = 0;
        int minLength = Math.min(a.components.length, b.components.length);

        for (int i = 0; i < minLength; i++) {
            sum += a.components[i] * b.components[i];
        }

        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (components.length == 1) {
            sb.append("{ ").append(components[0]).append(" }");

            return sb.toString();
        }

        sb.append("{ ").append(components[0]).append(", ");

        for (int i = 1; i < components.length - 1; i++) {
            sb.append(components[i]).append(", ");
        }

        sb.append(components[components.length - 1]).append(" }");

        return sb.toString();
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