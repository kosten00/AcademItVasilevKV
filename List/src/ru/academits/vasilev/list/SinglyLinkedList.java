package ru.academits.vasilev.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
        head = null;
        count = 0;
    }

    public void add(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public int getSize() {
        return count;
    }

    public T getFirstElement() {
        return head.getData();
    }

    public T getElement(int index) {
        int counter = 0;

        for (ListItem<T> p = head; ; p = p.getNext(), counter++) {
            if (counter == index) {
                return p.getData();
            }
        }
    }

    public void addElement(int index, T data) {
        if (index == 0) {
            this.add(data);
        } else {
            int counter = 0;

            for (ListItem<T> p = head; ; p = p.getNext(), counter++) {
                if (counter == index - 1) {
                    p.setNext(new ListItem<>(data, p.getNext()));
                    count++;

                    break;
                }
            }
        }
    }

    public T replaceElement(int index, T data) {
        int counter = 0;

        for (ListItem<T> p = head; ; p = p.getNext(), counter++) {
            if (counter == index) {
                ListItem<T> temp = new ListItem<T>(p.getData());

                p.setData(data);

                return temp.getData();
            }
        }
    }

    public T removeFirstElement() {
        ListItem<T> temp = head;

        head = head.getNext();
        count--;

        return temp.getData();
    }

    public T removeElement(int index) {
        if (index == 0) {
            ListItem<T> temp = this.head;

            this.removeFirstElement();

            return temp.getData();
        } else {
            int counter = 0;

            for (ListItem<T> p = head; ; p = p.getNext(), counter++) {
                if (counter == index - 1) {
                    ListItem<T> temp = new ListItem<>(p.getNext().getData());

                    p.setNext(p.getNext().getNext());
                    count--;

                    return temp.getData();
                }
            }
        }
    }

    public boolean removeData(T data) {
        if (head.getData() == data) {
            head = head.getNext();
            count--;

            return true;
        } else {
            for (ListItem<T> p = head; p != null; p = p.getNext()) {
                if (p.getNext().getData().equals(data)) {
                    p.setNext(p.getNext().getNext());
                    count--;

                    return true;
                }
            }
        }
        return false;
    }

    public void print() {
        int i = 0;

        for (ListItem<T> p = head; p != null; p = p.getNext(), i++) {
            System.out.println(i + ": " + p.getData());
        }
    }
}