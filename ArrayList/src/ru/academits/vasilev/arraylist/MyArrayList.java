package ru.academits.vasilev.arraylist;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 10;

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of the list size = " + size);
        }
    }

    private void checkSize() {
        if (size == items.length) {
            growCapacity();
        }
    }

    private void growCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity can't be less or equal to 0!");
        }

        items = (T[]) new Object[capacity];
    }

    public void ensureCapacity(int capacity) {
        if (capacity > items.length) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    public void trimToSize() {
        if (items.length > size) {
            items = Arrays.copyOf(items, size);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Concurrent list modification during iteration through! ");
            }
            currentIndex++;

            if (currentIndex >= size) {
                throw new NoSuchElementException("Index is out of the list's size");
            }

            if (currentIndex >= items.length) {
                throw new ConcurrentModificationException("Concurrent list size modification during iteration through! ");
            }
            return items[currentIndex];
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T1> T1[] toArray(T1[] objects) {
        if (objects.length < size) {
            return (T1[]) Arrays.copyOf(items, size, objects.getClass());
        }
        System.arraycopy(items, 0, objects, 0, size);

        if (objects.length > size) {
            objects[size] = null;
        }
        return objects;
    }

    @Override
    public boolean add(T item) {
        checkSize();

        items[size] = item;
        modCount++;
        size++;

        return true;
    }

    @Override
    public boolean remove(Object object) {
        int objectIndex = indexOf(object);

        checkIndex(objectIndex);

        System.arraycopy(items, objectIndex + 1, items, objectIndex, size - objectIndex);

        modCount++;
        size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        Object[] objects = collection.toArray();

        if (objects.length == 0) {
            return false;
        }
        checkIndex(indexOf(objects[0]));

        for (int i = 1, j = indexOf(objects[i]); j < objects.length; i++, j++) {
            if (objects[i] == null) {
                if (items[j] != null) {
                    return false;
                }
            } else if (!objects[i].equals(items[j])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        addAll(size, collection);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        checkIndex(index);

        if (collection.size() == 0) {
            return false;
        }
        size += collection.size();

        int i = index;
        for (T value : collection) {
            add(i, value);

            i++;
        }

        modCount++;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (!containsAll(collection)) {
            return false;
        }
        Object[] objects = collection.toArray();

        int start = getStartIndex(objects);
        int end = start + objects.length;

        System.arraycopy(items, end, items, start, size - end);

        int sizeBeforeChange = size;
        size -= objects.length;
        removeExcessItems(sizeBeforeChange);

        modCount++;
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (!containsAll(collection)) {
            return false;
        }
        Object[] objects = collection.toArray();

        int start = getStartIndex(objects);
        int end = start + objects.length;

        System.arraycopy(items, start, items, 0, end - start);
        int sizeBeforeChange = size;
        size = objects.length;
        removeExcessItems(sizeBeforeChange);

        modCount++;
        return true;
    }

    private int getStartIndex(Object[] objects) {
        return Collections.indexOfSubList(Arrays.asList(items), Arrays.asList(objects));
    }

    private void removeExcessItems(int sizeBeforeChange) {
        for (int i = size; i < sizeBeforeChange; i++) {
            items[i] = null;
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        size = 0;
        modCount++;
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public T set(int index, T item) {
        checkIndex(index);

        T oldItem = items[index];

        items[index] = item;
        return oldItem;
    }

    @Override
    public void add(int index, T item) {
        checkIndex(index);
        checkSize();

        System.arraycopy(items, index, items, index + 1, size - index);

        modCount++;
        items[index] = item;
        size++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T itemToRemove = items[index];

        int sizeBeforeChange = size;

        System.arraycopy(items, index + 1, items, index, (size - 1) - index);
        modCount++;
        size--;
        removeExcessItems(sizeBeforeChange);

        return itemToRemove;
    }

    @Override
    public int indexOf(Object object) {
        if (object == null) {
            for (int i = 0; i < size; i++) {
                if (items[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < items.length; i++) {
                if (object.equals(items[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        if (object == null) {
            for (int i = items.length - 1; i >= 0; i--) {
                if (items[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = items.length - 1; i >= 0; i--) {
                if (object.equals(items[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}