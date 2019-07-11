package ru.academits.vasilev.shapes;

import java.util.Arrays;
import java.util.Comparator;

public class ShapesMain {
    public static Shape getMaxAreaShape(Shape[] array) {
        Comparator<Shape> areaComparator = new Comparator<Shape>() {
            public int compare(Shape e1, Shape e2) {
                return (int) (e2.getArea() - e1.getArea());
            }
        };

        Arrays.sort(array, areaComparator);

        return array[0];
    }

    public static Shape getSecondPerimeterShape(Shape[] array) {
        Comparator<Shape> perimeterComparator = new Comparator<Shape>() {
            public int compare(Shape e1, Shape e2) {
                return (int) (e2.getPerimeter() - e1.getPerimeter());
            }
        };

        Arrays.sort(array, perimeterComparator);

        return array[1];
    }

    public static void main(String[] args) {
        Shape[] array = new Shape[8];

        array[0] = new Square(10);
        array[1] = new Rectangle(2, 4);
        array[2] = new Triangle(0, 50, 0, 0, 50, 0);
        array[3] = new Circle(2);
        array[4] = new Square(12);
        array[5] = new Circle(6);
        array[6] = new Rectangle(5, 9);
        array[7] = new Triangle(10, 11, 0, 11, 5, 0);

        System.out.println("Shape with max area is: \n" + getMaxAreaShape(array));

        System.out.println("Shape with second largest perimeter is: \n" + getSecondPerimeterShape(array));
    }
}