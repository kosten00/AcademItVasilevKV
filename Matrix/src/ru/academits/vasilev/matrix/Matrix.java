package ru.academits.vasilev.matrix;

import ru.academits.vasilev.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] elementsRaw;

    private double calculateDeterminant(double[][] matrixArray) {
        double determinant = 0;

        if (matrixArray.length == 2) {
            determinant = matrixArray[0][0] * matrixArray[1][1] - matrixArray[1][0] * matrixArray[0][1];
        } else {
            int k;

            for (int i = 0; i < matrixArray.length; i++) {
                if (i % 2 == 1) {
                    k = -1;
                } else {
                    k = 1;
                }

                determinant += k * matrixArray[0][i] * calculateDeterminant(getMinor(matrixArray, i));
            }
        }

        return determinant;
    }

    private double[][] getMinor(double[][] matrixArray, int column) {
        int minorLength = matrixArray.length - 1;

        double[][] minor = new double[minorLength][minorLength];

        int skipRaw = 0;

        for (int i = 0; i <= minorLength; i++) {
            int skipColumn = 0;

            for (int j = 0; j <= minorLength; j++) {
                if (i == 0) {
                    skipRaw = 1;
                } else {
                    if (j == column) {
                        skipColumn = 1;
                    } else {
                        minor[i - skipRaw][j - skipColumn] = matrixArray[i][j];
                    }
                }
            }
        }

        return minor;
    }

    public Matrix(int raw, int column) {
        elementsRaw = new Vector[raw];

        for (int i = 0; i < raw; i++) {
            elementsRaw[i] = new Vector(column);
        }
    }

    public Matrix(Matrix matrix) {
        elementsRaw = Arrays.copyOf(matrix.elementsRaw, matrix.elementsRaw.length);
    }

    public Matrix(double[][] array) {
        elementsRaw = new Vector[array.length];

        int maxLength = array[0].length;

        for (int i = 1; i < array.length; i++) {
            if (maxLength < array[i].length) {
                maxLength = array[i].length;
            }
        }

        for (int i = 0; i < elementsRaw.length; i++) {
            elementsRaw[i] = new Vector(maxLength, array[i]);
        }
    }

    public Matrix(Vector[] elementsRaw) {
        int maxSize = elementsRaw[0].getSize();

        for (int i = 1; i < elementsRaw.length; i++) {
            if (maxSize < elementsRaw[i].getSize()) {
                maxSize = elementsRaw[i].getSize();
            }
        }

        this.elementsRaw = new Vector[elementsRaw.length];

        for (int i = 0; i < this.elementsRaw.length; i++) {
            this.elementsRaw[i] = new Vector(maxSize);

            for (int j = 0; j < maxSize; j++) {
                if (j >= elementsRaw[i].getSize()) {
                    break;
                }

                this.elementsRaw[i].setElement(j, elementsRaw[i].getElement(j));
            }
        }
    }

    public int[] getSize() {
        int raw = elementsRaw.length;
        int column = elementsRaw[0].getSize();

        return new int[]{raw, column};
    }

    public Vector getRaw(int index) {
        return elementsRaw[index];
    }

    public void setRaw(int index, Vector elementsRaw) {
        this.elementsRaw[index] = elementsRaw;
    }

    public Vector getColumn(int index) {
        double[] array = new double[elementsRaw.length];

        for (int i = 0; i < elementsRaw.length; i++) {
            array[i] = elementsRaw[i].getElement(index);
        }

        return new Vector(array);
    }

    public void transpose() {
        Vector[] transpose = new Vector[elementsRaw[0].getSize()];

        for (int i = 0; i < transpose.length; i++) {
            transpose[i] = getColumn(i);
        }

        elementsRaw = transpose;
    }

    public double getDeterminant() {
        if (this.elementsRaw.length != this.elementsRaw[0].getSize()) {
            throw new IllegalArgumentException("to calculate determinant matrix must be square!");
        }

        double[][] matrixArray = new double[elementsRaw.length][elementsRaw[0].getSize()];

        for (int i = 0; i < matrixArray.length; i++) {
            for (int j = 0; j < matrixArray[i].length; j++) {
                matrixArray[i][j] = elementsRaw[i].getElement(j);
            }
        }

        return calculateDeterminant(matrixArray);
    }

    public void multiply(double number) {
        for (Vector vector : elementsRaw) {
            for (int j = 0; j < vector.getSize(); j++) {
                vector.setElement(j, vector.getElement(j) * number);
            }
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(elementsRaw);
    }
}