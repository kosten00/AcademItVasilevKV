package ru.academits.vasilev.shapes.main;

import ru.academits.vasilev.shapes.*;

import static ru.academits.vasilev.shapes.CompareArea.getMaxAreaShape;
import static ru.academits.vasilev.shapes.ComparePerimeter.getSecondPerimeterShape;

public class ShapesMain {
    public static void main(String[] args) {
        Shape[] array = {new Square(10), new Rectangle(2, 4), new Triangle(0, 50, 0, 0, 50, 0), new Circle(2), new Square(12), new Circle(6), new Rectangle(5, 9), new Triangle(10, 11, 0, 11, 5, 0)};

        System.out.println("Shape with max area is: " + getMaxAreaShape(array));

        System.out.println("Shape with second largest perimeter is: " + getSecondPerimeterShape(array));
    }
}