package ru.academits.vasilev.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
        head = null;
        count = 0;
    }

    public SinglyLinkedList(SinglyLinkedList<T> list) {
        list.checkHead();

        ListItem<T> listItemCopy = new ListItem<>(list.head.getData());

        head = listItemCopy;

        for (ListItem<T> p = list.head; p.getNext() != null; p = p.getNext()) {
            listItemCopy.setNext(new ListItem<>(p.getNext().getData()));

            listItemCopy = listItemCopy.getNext();
        }
        count = list.count;
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

    private ListItem<T> iterateTo(ListItem<T> start, int end) {
        int i = 0;

        ListItem<T> current = start;

        while (i != end) {
            ListItem<T> next = current.getNext();

            current = next;
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
        checkHead();
        checkInputIndex(index);

        return iterateTo(head, index).getData();
    }

    public void addElement(int index, T data) {
        checkHead();
        checkInputIndex(index);

        if (index == 0) {
            addFirst(data);
        } else {
            ListItem<T> itemToAddAfter = iterateTo(head, index - 1);

            itemToAddAfter.setNext(new ListItem<>(data, itemToAddAfter.getNext()));
            count++;
        }
    }

    public T replaceElement(int index, T data) {
        checkHead();
        checkInputIndex(index);

        ListItem<T> itemToReplaceData = iterateTo(head, index);

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
        checkHead();
        checkInputIndex(index);

        if (index == 0) {
            ListItem<T> removalElement = head;
            removeFirstElement();

            return removalElement.getData();
        }
        ListItem<T> itemToRemoveAfter = iterateTo(head, index - 1);

        ListItem<T> removalElement = new ListItem<>(itemToRemoveAfter.getNext().getData());

        itemToRemoveAfter.setNext(itemToRemoveAfter.getNext().getNext());
        count--;
        return removalElement.getData();
    }

    public boolean removeData(T data) {
        checkHead();

        if (data == null) {
            if (head.getData() == null) {
                head = head.getNext();
                count--;
                return true;
            }
            for (ListItem<T> p = head; p != null; p = p.getNext()) {
                if (p.getNext().getData() == null) {
                    p.setNext(p.getNext().getNext());
                    count--;
                    return true;
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