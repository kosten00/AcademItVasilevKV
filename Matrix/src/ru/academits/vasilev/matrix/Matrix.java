package ru.academits.vasilev.matrix;

import ru.academits.vasilev.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] elementsRow;

    public Matrix(int row, int column) {
        elementsRow = new Vector[row];

        for (int i = 0; i < row; i++) {
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

    public Matrix(Vector[] elementsRow) {
        int maxSize = elementsRow[0].getSize();

        for (int i = 1; i < elementsRow.length; i++) {
            if (maxSize < elementsRow[i].getSize()) {
                maxSize = elementsRow[i].getSize();
            }
        }

        this.elementsRow = new Vector[elementsRow.length];

        for (int i = 0; i < this.elementsRow.length; i++) {
            this.elementsRow[i] = new Vector(maxSize);

            for (int j = 0; j < maxSize; j++) {
                if (j >= elementsRow[i].getSize()) {
                    break;
                }

                this.elementsRow[i].setElement(j, elementsRow[i].getElement(j));
            }
        }
    }

    public int[] getSize() {
        int row = elementsRow.length;

        int column = elementsRow[0].getSize();

        return new int[]{row, column};
    }

    public Vector getRow(int index) {
        return elementsRow[index];
    }

    public void setRow(int index, Vector elementsRaw) {
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
        int rowsCount = getSize()[0];

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

    public void getSum(Matrix matrix) {
        if (!Arrays.equals(this.getSize(), matrix.getSize())) {
            throw new IllegalArgumentException("Matrix should have same size!");
        }

        int columnsCount = getSize()[1];
        int rowsCount = getSize()[0];

        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                elementsRow[i].setElement(j, elementsRow[i].getElement(j) + matrix.elementsRow[i].getElement(j));
            }
        }
    }

    public void getDifference(Matrix matrix) {
        if (!Arrays.equals(this.getSize(), matrix.getSize())) {
            throw new IllegalArgumentException("Matrix should have same size!");
        }

        int columnsCount = getSize()[1];
        int rowsCount = getSize()[0];

        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                elementsRow[i].setElement(j, elementsRow[i].getElement(j) - matrix.elementsRow[i].getElement(j));
            }
        }
    }

    public static Matrix getSum(Matrix m1, Matrix m2) {
        Matrix sum = new Matrix(m1);

        sum.getSum(m2);

        return sum;
    }

    public static Matrix getDifference(Matrix m1, Matrix m2) {
        Matrix difference = new Matrix(m1);

        difference.getDifference(m2);

        return difference;
    }

    public static Matrix multiply(Matrix m1, Matrix m2) {
        if (m1.getSize()[0] != m2.getSize()[1]) {
            throw new IllegalArgumentException("First matrix rows count should be equal to second matrix columns count");
        }

        int columnsCount = m2.getSize()[1];
        int rowsCount = m1.getSize()[0];

        Matrix multiplication = new Matrix(rowsCount, columnsCount);

        Matrix transposed = new Matrix(m2);

        transposed.transpose();

        for (int i = 0; i < rowsCount; i++) {
            multiplication.elementsRow[i].setElement
                    (i, Vector.getScalarMultiplication
                            (m1.elementsRow[i], transposed.elementsRow[i]));
            for (int j = columnsCount - 1; j >= 0; j--) {
                multiplication.elementsRow[i].setElement
                        (j, Vector.getScalarMultiplication
                                (m1.elementsRow[i], transposed.elementsRow[j]));
            }
        }

        return multiplication;
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
}