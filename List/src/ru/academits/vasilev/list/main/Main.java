package ru.academits.vasilev.list.main;

import ru.academits.vasilev.list.SinglyLinkedList;
import ru.academits.vasilev.matrix.Matrix;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        Matrix matrix = new Matrix(2, 2);

        System.out.println("List list: ");

        list.add("some string");
        list.add(666);
        list.add(11.9);
        list.add(matrix);

        list.print();
        System.out.println();

        System.out.println("return list size: " + list.getSize());

        System.out.println("return first element from list: " + list.getFirstElement());

        System.out.println("return element with index = 2: " + list.getElement(2));
    }
}
