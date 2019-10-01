package ru.academits.vasilev.matrix;

import ru.academits.vasilev.vector.Vector;

public class Matrix {
    private Vector[] rows;

    private void checkInputSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("You can't create matrix with length <= 0!");
        }
    }

    private void checkInputIndex(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Row index is out of range");
        }
    }

    private void checkMatrixSize(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Matrix should have same size!");
        }
    }

    public Matrix(int rowsNumber, int columnsNumber) {
        checkInputSize(rowsNumber);
        checkInputSize(columnsNumber);

        rows = new Vector[rowsNumber];

        for (int i = 0; i < rowsNumber; i++) {
            rows[i] = new Vector(columnsNumber);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        checkInputSize(array.length);

        int maxLength = array[0].length;

        for (int i = 1; i < array.length; i++) {
            if (maxLength < array[i].length) {
                maxLength = array[i].length;
            }
        }
        checkInputSize(maxLength);

        rows = new Vector[array.length];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(maxLength, array[i]);
        }
    }

    public Matrix(Vector[] vectorsArray) {
        checkInputSize(vectorsArray.length);

        int maxSize = vectorsArray[0].getSize();

        for (int i = 1; i < vectorsArray.length; i++) {
            if (maxSize < vectorsArray[i].getSize()) {
                maxSize = vectorsArray[i].getSize();
            }
        }
        checkInputSize(maxSize);

        rows = new Vector[vectorsArray.length];

        for (int i = 0; i < this.rows.length; i++) {
            rows[i] = new Vector(maxSize);

            rows[i].sum(vectorsArray[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        checkInputIndex(index);

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector row) {
        checkInputIndex(index);

        rows[index] = new Vector(row);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Column index is out of range");
        }
        double[] array = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            array[i] = rows[i].getElement(index);
        }

        return new Vector(array);
    }

    public void transpose() {
        Vector[] transpose = new Vector[getColumnsCount()];

        for (int i = 0; i < transpose.length; i++) {
            transpose[i] = getColumn(i);
        }

        rows = transpose;
    }

    public void multiplyByScalar(double number) {
        for (Vector vector : rows) {
            vector.multiplyByScalar(number);
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
        if (rows.length != getColumnsCount()) {
            throw new IllegalArgumentException("to calculate determinant matrix must be square!");
        }

        if (rows.length == 1) {
            return rows[0].getElement(0);
        }

        double[][] matrixArray = new double[rows.length][getColumnsCount()];

        for (int i = 0; i < matrixArray.length; i++) {
            for (int j = 0; j < matrixArray[i].length; j++) {
                matrixArray[i][j] = rows[i].getElement(j);
            }
        }

        return calculateDeterminant(matrixArray);
    }

    public Vector multiply(Vector vector) {
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Vector's length must be equal to the number of matrix columns");
        }
        Vector result = new Vector(getRowsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            result.setElement(i, Vector.getScalarMultiplication(rows[i], vector));
        }

        return result;
    }

    public void sum(Matrix matrix) {
        checkMatrixSize(matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].sum(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkMatrixSize(matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix m1, Matrix m2) {
        m1.checkMatrixSize(m2);

        Matrix sum = new Matrix(m1);

        sum.sum(m2);

        return sum;
    }

    public static Matrix getDifference(Matrix m1, Matrix m2) {
        m1.checkMatrixSize(m2);

        Matrix difference = new Matrix(m1);

        difference.subtract(m2);

        return difference;
    }

    public static Matrix multiply(Matrix m1, Matrix m2) {
        if (m1.getColumnsCount() != m2.getRowsCount()) {
            throw new IllegalArgumentException("First matrix rows count should be equal to second matrix columns count");
        }

        Matrix multiplication = new Matrix(m1.getRowsCount(), m2.getColumnsCount());

        for (int i = 0; i < multiplication.rows.length; i++) {
            for (int j = 0; j < multiplication.getColumnsCount(); j++) {
                multiplication.rows[i].setElement(j, Vector.getScalarMultiplication(m1.rows[i], m2.getColumn(j)));
            }
        }

        return multiplication;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");

        for (Vector vector : rows) {
            sb.append(vector).append(", ");
        }
        sb.delete(sb.lastIndexOf(","), sb.length());
        sb.append(" }");

        return sb.toString();
    }
}