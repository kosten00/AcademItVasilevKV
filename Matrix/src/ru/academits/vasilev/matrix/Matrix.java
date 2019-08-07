package ru.academits.vasilev.matrix;

import ru.academits.vasilev.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] elementsRaw;

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

    @Override
    public String toString() {
        return Arrays.toString(elementsRaw);
    }
}