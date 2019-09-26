package ru.academits.vasilev.list.main;

import ru.academits.vasilev.list.SinglyLinkedList;
import ru.academits.vasilev.matrix.Matrix;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        Matrix matrix = new Matrix(2, 2);
        System.out.println("List list: ");

        list.addFirst("some string");
        list.addFirst(666);
        list.addFirst(11.9);
        list.addFirst(matrix);

        list.print();

        SinglyLinkedList list2 = new SinglyLinkedList(list);

        list2.print();

        list2.addElement(4, "new element");

        list2.print();

    }
}
