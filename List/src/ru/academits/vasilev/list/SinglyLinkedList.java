package ru.academits.vasilev.list;

import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(SinglyLinkedList<T> list) {
        if (list.head == null) {
            new SinglyLinkedList<>();
        } else {
            ListItem<T> listItemCopy = new ListItem<>(list.head.getData());

            head = listItemCopy;

            for (ListItem<T> p = list.head; p.getNext() != null; p = p.getNext()) {
                listItemCopy.setNext(new ListItem<>(p.getNext().getData()));

                listItemCopy = listItemCopy.getNext();
            }
            count = list.count;
        }
    }

    private void checkInputIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of list's bounds");
        }
    }

    private void checkHead() {
        if (head == null) {
            throw new NullPointerException("List is empty");
        }
    }

    private ListItem<T> iterateTo(int index) {
        int i = 0;

        ListItem<T> current = head;

        while (i != index) {
            current = current.getNext();

            i++;
        }
        return current;
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public int getSize() {
        return count;
    }

    public T getFirstElement() {
        checkHead();

        return head.getData();
    }

    public T getElement(int index) {
        checkInputIndex(index);

        return iterateTo(index).getData();
    }

    public void addElement(int index, T data) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Index is out of list's bounds");
        }

        if (head == null) {
            head = new ListItem<>(data);
        } else {
            if (index == 0) {
                addFirst(data);
            } else {
                ListItem<T> itemToAddAfter = iterateTo(index - 1);

                itemToAddAfter.setNext(new ListItem<>(data, itemToAddAfter.getNext()));
            }
        }

        count++;
    }

    public T setElement(int index, T data) {
        checkInputIndex(index);

        ListItem<T> itemToReplaceData = iterateTo(index);

        ListItem<T> itemToReturnData = itemToReplaceData;

        itemToReplaceData.setData(data);
        return itemToReturnData.getData();
    }

    public T removeFirstElement() {
        checkHead();

        ListItem<T> removalElement = head;

        head = head.getNext();
        count--;
        return removalElement.getData();
    }

    public T removeElement(int index) {
        checkInputIndex(index);

        if (index == 0) {
            return removeFirstElement();
        }
        ListItem<T> itemToRemoveAfter = iterateTo(index - 1);

        ListItem<T> removalElement = new ListItem<>(itemToRemoveAfter.getNext().getData());

        itemToRemoveAfter.setNext(itemToRemoveAfter.getNext().getNext());
        count--;
        return removalElement.getData();
    }

    public boolean removeData(T data) {
        checkHead();

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (Objects.equals(p.getNext().getData(), data)) {
                p.setNext(p.getNext().getNext());
                count--;
                return true;
            }
        }

        return false;
    }

    public void revert() {
        checkHead();

        ListItem<T> previous = null;
        ListItem<T> current = head;

        while (current != null) {
            ListItem<T> next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }
        head = previous;
    }

    public void print() {
        checkHead();

        int i = 0;

        for (ListItem<T> p = head; p != null; p = p.getNext(), i++) {
            System.out.println(i + ": " + p.getData());
        }
    }
}