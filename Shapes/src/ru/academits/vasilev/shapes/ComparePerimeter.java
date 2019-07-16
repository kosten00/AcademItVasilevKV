package ru.academits.vasilev.shapes;

import java.util.Arrays;
import java.util.Comparator;

public class ComparePerimeter {
    public static Shape getSecondPerimeterShape(Shape[] array) {
        Comparator<Shape> perimeterComparator = new Comparator<Shape>() {
            public int compare(Shape e1, Shape e2) {
                return Double.compare(e2.getPerimeter(), e1.getPerimeter());
            }
        };

        Arrays.sort(array, perimeterComparator);

        return array[1];
    }
}