package ru.academits.vasilev.shapes;

public class Main {
    public static void main(String[] args) {
        Shape s = new Square(5);
        System.out.println(s.getArea());

        Shape r = new Rectangle(2, 4);
        System.out.println(r.getPerimeter());

        Shape t = new Triangle(11, 4, 8, 2, 11, 1);
        System.out.println(t.getArea());

        Shape c = new Circle(9);

        System.out.println(c.getPerimeter());
    }
}