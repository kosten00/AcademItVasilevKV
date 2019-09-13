package ru.academits.vasilev.matrix;

import ru.academits.vasilev.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] elementsRow;

    public Matrix(int raw, int column) {
        elementsRow = new Vector[raw];

        for (int i = 0; i < raw; i++) {
            elementsRow[i] = new Vector(column);
        }
    }

    public Matrix(Matrix matrix) {
        elementsRow = Arrays.copyOf(matrix.elementsRow, matrix.elementsRow.length);
    }

    public Matrix(double[][] array) {
        elementsRow = new Vector[array.length];

        int maxLength = array[0].length;

        for (int i = 1; i < array.length; i++) {
            if (maxLength < array[i].length) {
                maxLength = array[i].length;
            }
        }

        for (int i = 0; i < elementsRow.length; i++) {
            elementsRow[i] = new Vector(maxLength, array[i]);
        }
    }

    public Matrix(Vector[] elementsRaw) {
        int maxSize = elementsRaw[0].getSize();

        for (int i = 1; i < elementsRaw.length; i++) {
            if (maxSize < elementsRaw[i].getSize()) {
                maxSize = elementsRaw[i].getSize();
            }
        }

        this.elementsRow = new Vector[elementsRaw.length];

        for (int i = 0; i < this.elementsRow.length; i++) {
            this.elementsRow[i] = new Vector(maxSize);

            for (int j = 0; j < maxSize; j++) {
                if (j >= elementsRaw[i].getSize()) {
                    break;
                }

                this.elementsRow[i].setElement(j, elementsRaw[i].getElement(j));
            }
        }
    }

    public int[] getSize() {
        int raw = elementsRow.length;

        int column = elementsRow[0].getSize();

        return new int[]{raw, column};
    }

    public Vector getRaw(int index) {
        return elementsRow[index];
    }

    public void setRaw(int index, Vector elementsRaw) {
        this.elementsRow[index] = elementsRaw;
    }

    public Vector getColumn(int index) {
        double[] array = new double[elementsRow.length];

        for (int i = 0; i < elementsRow.length; i++) {
            array[i] = elementsRow[i].getElement(index);
        }

        return new Vector(array);
    }

    public void transpose() {
        Vector[] transpose = new Vector[elementsRow[0].getSize()];

        for (int i = 0; i < transpose.length; i++) {
            transpose[i] = getColumn(i);
        }

        elementsRow = transpose;
    }

    public void multiplyByScalar(double number) {
        for (Vector vector : elementsRow) {
            for (int j = 0; j < vector.getSize(); j++) {
                vector.setElement(j, vector.getElement(j) * number);
            }
        }
    }

    private double calculateDeterminant(double[][] matrixArray) {
        if (matrixArray.length == 2) {
            return matrixArray[0][0] * matrixArray[1][1] - matrixArray[1][0] * matrixArray[0][1];
        }
        double determinant = 0;

        for (int i = 0; i < matrixArray.length; i++) {
            if (i % 2 == 1) {
                determinant -= matrixArray[0][i] * calculateDeterminant(getMinor(matrixArray, i));
            } else {
                determinant += matrixArray[0][i] * calculateDeterminant(getMinor(matrixArray, i));
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

    public double getDeterminant() {
        if (this.elementsRow.length != this.elementsRow[0].getSize()) {
            throw new IllegalArgumentException("to calculate determinant matrix must be square!");
        }

        double[][] matrixArray = new double[elementsRow.length][elementsRow[0].getSize()];

        for (int i = 0; i < matrixArray.length; i++) {
            for (int j = 0; j < matrixArray[i].length; j++) {
                matrixArray[i][j] = elementsRow[i].getElement(j);
            }
        }

        return calculateDeterminant(matrixArray);
    }

    public void multiply(Vector vector) {
        int columnsCount = getSize()[1];
        int rowsCount = elementsRow.length;

        if (vector.getSize() > columnsCount) {
            throw new IllegalArgumentException("Vector's length must be equal to the number of matrix columns");
        }

        Vector[] result = new Vector[rowsCount];
        for (int i = 0; i < rowsCount; i++) {
            result[i] = new Vector(1);
            result[i].setElement(0, Vector.getScalarMultiplication(elementsRow[i], vector));
        }

        elementsRow = result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");

        for (Vector vector : elementsRow) {
            sb.append(vector).append(", ");
        }
        sb.delete(sb.lastIndexOf(","), sb.length());
        sb.append(" }");

        return sb.toString();
    }

    /*Сложение матриц
     Вычитание матриц
     Статические методы:
     Сложение матриц
     Вычитание матриц
     Умножение матриц
     */
}