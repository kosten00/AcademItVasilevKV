package ru.academits.vasilev.list;
/**
 * 1. Второй конструктор работает неверно.
 * Должен создаваться полностью новый список, и его узлы должны быть новыми
 *
 * 4. Во многих методах дублируется итерирование до узла с нужным индексом.
 * Нужно сделать вспомогательный метод
**/

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
        head = null;
        count = 0;
    }

    public SinglyLinkedList(SinglyLinkedList<T> list) {
        list.checkHead();

        head = list.head;
        count = list.count;
    }

    private void checkInputIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of list bounds");
        }
    }

    private void checkHead() {
        if (head == null) {
            throw new NullPointerException("List is empty");
        }
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

        int i = 0;

        for (ListItem<T> p = head; ; p = p.getNext(), i++) {
            if (i == index) {
                return p.getData();
            }
        }
    }

    public void addElement(int index, T data) {
        checkInputIndex(index);

        if (index == 0) {
            addFirst(data);
        } else {
            int i = 0;

            for (ListItem<T> p = head; ; p = p.getNext(), i++) {
                if (i == index - 1) {
                    p.setNext(new ListItem<>(data, p.getNext()));
                    count++;

                    break;
                }
            }
        }
    }

    public T replaceElement(int index, T data) {
        checkInputIndex(index);

        int i = 0;

        for (ListItem<T> p = head; ; p = p.getNext(), i++) {
            if (i == index) {
                ListItem<T> temp = new ListItem<T>(p.getData());

                p.setData(data);

                return temp.getData();
            }
        }
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
            ListItem<T> removalElement = head;

            removeFirstElement();

            return removalElement.getData();
        }

        int i = 0;

        for (ListItem<T> p = head; ; p = p.getNext(), i++) {
            if (i == index - 1) {
                ListItem<T> removalElement = new ListItem<>(p.getNext().getData());

                p.setNext(p.getNext().getNext());
                count--;

                return removalElement.getData();
            }
        }
    }

    public boolean removeData(T data) {
        checkHead();

        if (data == null) {
            if (head.getData() == null) {
                head = head.getNext();
                count--;

                return true;
            } else {
                for (ListItem<T> p = head; p != null; p = p.getNext()) {
                    if (p.getNext().getData() == null) {
                        p.setNext(p.getNext().getNext());
                        count--;

                        return true;
                    }
                }
            }
            return false;
        }

        if (head.getData().equals(data)) {
            head = head.getNext();
            count--;

            return true;
        }

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (p.getNext().getData().equals(data)) {
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