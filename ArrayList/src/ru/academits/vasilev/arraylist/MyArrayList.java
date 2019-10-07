package ru.academits.vasilev.arraylist;

import java.util.*;

/*

6. removeAll и retainAll:
- не должен вызывать containsAll, это слишком долго.
И это неверно по смыслу
- нужно обойтись без преобразования коллекции в массив
 */

public class MyArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 10;

    private void checkIndex(int index, boolean isAdditive) {
        if (index < 0 || !isAdditive && index >= size || index > size) {
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

            if (!hasNext()) {
                throw new NoSuchElementException("Index is out of the list's size");
            }

            if (currentIndex >= items.length) {
                throw new ConcurrentModificationException("Concurrent list size modification during iteration through! ");
            }
            currentIndex++;

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

        if (objectIndex == -1) {
            return false;
        }

        System.arraycopy(items, objectIndex + 1, items, objectIndex, size - objectIndex);

        modCount++;
        size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection.size() == 0) {
            return false;
        }

        for (Object object : collection) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return addAll(size, collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        checkIndex(index, true);

        int collectionSize = collection.size();

        if (collectionSize == 0) {
            return false;
        }
        size += collectionSize;

        ensureCapacity(size);
        System.arraycopy(items, index, items, index + collectionSize, collectionSize);

        int i = index;
        for (T value : collection) {
            items[i] = value;

            i++;
        }

        modCount++;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection.size() == 0) {
            return false;
        }

        for (Object object : collection) {
            while (indexOf(object) != -1) {
                remove(object);
            }
        }

        return true;
    }

    //TODO попробовать реализовать это и, по аналогии, ретеинАлл:
    public boolean removeAllTemp(Collection<?> collection) {
        T[] newItems = (T[]) new Object[size];

        for (Object object : collection) {
            int previousIndex = 0;

            while (indexOf(object) != -1) {
                int currentIndex = indexOf(object);

                size--;

                if (previousIndex < currentIndex) {
                    for (int i = previousIndex; i < currentIndex; i++) {
                        newItems[i] = items[i];
                    }

                }
                previousIndex = currentIndex + 1;
            }
        }
        size--;

        items = Arrays.copyOf(newItems, size);

        modCount++;
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection.size() == 0) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!collection.contains(items[i])) {
                remove(items[i]);

                i--;
            }
        }

        return true;
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
        checkIndex(index, false);

        return items[index];
    }

    @Override
    public T set(int index, T item) {
        checkIndex(index, false);

        T oldItem = items[index];

        items[index] = item;
        return oldItem;
    }

    @Override
    public void add(int index, T item) {
        checkIndex(index, true);
        checkSize();

        System.arraycopy(items, index, items, index + 1, size - index);

        modCount++;
        items[index] = item;
        size++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, false);

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
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], object)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], object)) {
                return i;
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