package ru.academits.vasilev.list;

/**
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

        count = list.count;

        ListItem<T> q = new ListItem<>(list.head.getData());

        head = q;

        for (ListItem<T> p = list.head; p.getNext() != null; p = p.getNext(), q = q.getNext()) {
            q.setNext(new ListItem<>(p.getNext().getData()));
        }
    }

    private void checkInputIndex(int index) {
        if (index < 0 || index > count) {
            throw new IllegalArgumentException("Index is out of list's bounds");
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

    private ListItem<T> iterateToIndex(ListItem<T> start, int end) {
        int i = 0;

        ListItem<T> current = start;

        while (i != end) {
            ListItem<T> next = current.getNext();

            current = next;

            i++;
        }

        return current;
    }

    public T getElement(int index) {
        checkInputIndex(index);

        return iterateToIndex(head, index).getData();
    }

    public void addElement(int index, T data) {
        checkInputIndex(index);

        if (index == 0) {
            addFirst(data);
        } else {
            ListItem<T> p = iterateToIndex(head, index - 1);

            p.setNext(new ListItem<>(data, p.getNext()));

            count++;
        }
    }

    public T replaceElement(int index, T data) {
        checkInputIndex(index);

        ListItem<T> p = iterateToIndex(head, index);

        ListItem<T> oldItem = p;

        p.setData(data);

        return oldItem.getData();
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