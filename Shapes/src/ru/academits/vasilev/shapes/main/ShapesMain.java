package ru.academits.vasilev.shapes.main;

import ru.academits.vasilev.shapes.*;

import java.util.Arrays;

public class ShapesMain {
    private static Shape getSecondPerimeter(Shape[] array) {
        Arrays.sort(array, new PerimeterComparator());
        return array[1];
    }

    private static Shape getMaxAreaShape(Shape[] array) {
        Arrays.sort(array, new AreaComparator());
        return array[0];
    }

    public static void main(String[] args) {
        Shape[] array = {
                new Square(10),
                new Rectangle(2, 4),
                new Triangle(0, 50, 0, 0, 50, 0),
                new Circle(2),
                new Square(12),
                new Circle(6),
                new Rectangle(5, 9),
                new Triangle(10, 11, 0, 11, 5, 0)
        };
        System.out.println("Shape with max area is: " + getMaxAreaShape(array));

        System.out.println("Shape with second largest perimeter is: " + getSecondPerimeter(array));
    }
}