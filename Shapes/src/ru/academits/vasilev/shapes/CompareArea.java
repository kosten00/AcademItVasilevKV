package ru.academits.vasilev.shapes;

import java.util.Arrays;
import java.util.Comparator;

public class CompareArea {
    public static Shape getMaxAreaShape(Shape[] array) {
        Comparator<Shape> areaComparator = new Comparator<Shape>() {
            public int compare(Shape e1, Shape e2) {
                return Double.compare(e2.getArea(), e1.getArea());
            }
        };

        Arrays.sort(array, areaComparator);

        return array[0];
    }
}