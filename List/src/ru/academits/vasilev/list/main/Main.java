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

        System.out.println("Reverting list: ");
        list.revert();

        System.out.println();

        list.print();

        System.out.println("Getting list size: ");
        System.out.println(list.getSize());

        System.out.println("Removing element by data (int 666), if list contains it: " + list.removeData(666));

        list.print();

        System.out.println("Removing element by index (2): " + list.removeElement(2));
        list.print();

        System.out.println("Removing first element: " + list.removeFirstElement());

        list.print();

        System.out.println("Replacing element with index = 0, with String: \"AAA\", removed element data = " + list.replaceElement(0, "AAA"));

        list.print();

        System.out.println("Adding element String \"BBB\" by index 1: ");
        list.addElement(1, "BBB");

        list.print();

        System.out.println("list2 is a copy of list: ");
        SinglyLinkedList list2 = new SinglyLinkedList(list);

        list2.print();
    }
}
