package ru.academits.vasilev.list.main;

import ru.academits.vasilev.list.SinglyLinkedList;
import ru.academits.vasilev.matrix.Matrix;

public class SingleLinkListMain {
    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        Matrix matrix = new Matrix(2, 2);
        System.out.println("List list: ");

        list.addFirst("some string");
        list.addFirst("666");
        list.addFirst("11.9");
        list.addFirst("matrix");

        list.print();

        SinglyLinkedList<String> list2 = new SinglyLinkedList(list);

        list2.print();

        list2.addElement(4, null);
        list2.addElement(5, "111");

        list2.print();

        list2.addElement(3,"sssss");
        list2.print();
        System.out.println(list2.removeData(null));
        list2.removeElement(4);

        list2.print();

        System.out.println(list2.getSize());
    }
}
