package ru.academits.vasilev.list.main;

import ru.academits.vasilev.list.SinglyLinkedList;
import ru.academits.vasilev.matrix.Matrix;

public class SingleLinkListMain {
    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.revert();

        System.out.println("List list: ");

        list.addFirst("some string");
        list.addFirst("666");
        list.addFirst("11.9");
        list.addFirst("matrix");
        System.out.println(list.setElement(2, "someData"));

        list.print();

        SinglyLinkedList<String> listnull = new SinglyLinkedList<>();

        SinglyLinkedList<String> list2 = new SinglyLinkedList(listnull);
        System.out.println(list2.removeData("666"));

        list2.print();

        list2.addElement(0, null);
        list2.addElement(1, "111");

        list2.print();

        list2.addElement(2, "sssss");
        list2.print();
        System.out.println(list2.removeData(null));

        list2.addFirst( null);

        list2.print();

        System.out.println(list2.setElement(1, "element"));

        list2.print();
    }
}
