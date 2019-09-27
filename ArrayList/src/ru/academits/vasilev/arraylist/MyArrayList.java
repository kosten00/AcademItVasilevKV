/*
 3. Многие методы сейчас не умеют работать с null данными.
 Например, indexOf
 8. В классе самого итератора не нужен метод iterator
 9. remove(Object) сделан неэффективно - поиск делается несколько раз.
 Кроме того, выдается неверный boolean и есть ошибки
 10. containsAll - нужно реализовать
 11. addAll, removeAll, retainAll - выдается неверный boolean
 12. addAll - нужно обойтись без преобразования коллекции в массив
 13. size - index > 0.
 Вместо этого лучше писать size > index или index < size
 14. addAll, removeAll - можно использовать += и -=
 15. removeExcessItems - счетчик можно объявить в цикле
 16. clear - есть ошибка
 17. set работает неверно
 18. Не должно быть пустых строк перед }
 19. Для выхода индекса за границы есть более подходящий тип исключения
 20. Не во всех методах правильно делается проверка индекса
 21. remove(int) - есть ошибка
 */

package ru.academits.vasilev.arraylist;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 10;

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index " + index + " is out of the list size = " + size);
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
    public boolean contains(Object item) {
        return indexOf(item) >= 0;
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

        public Iterator<T> iterator() {
            return new MyIterator();
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            return (T1[]) Arrays.copyOf(items, size, a.getClass());
        }
        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }
        return a;
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
    public boolean remove(Object o) {
        System.arraycopy(items, indexOf(o) + 1, items, indexOf(o), size - indexOf(o));
        modCount++;
        size--;

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        addAll(size, c);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIndex(index);

        Object[] a = c.toArray();

        while (a.length + size >= items.length) {
            growCapacity();
        }
        if (size - index > 0) {
            System.arraycopy(items, index, items, index + a.length, size - index);
        }
        System.arraycopy(a, 0, items, index, a.length);

        modCount++;
        size = size + a.length;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int start = getStartIndex(c.toArray());

        if (start == -1) {
            return false;
        }
        int end = start + c.size();

        System.arraycopy(items, end, items, start, size - end);

        int sizeBeforeChange = size;
        size = size - c.size();
        removeExcessItems(sizeBeforeChange);

        modCount++;
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int start = getStartIndex(c.toArray());

        if (start == -1) {
            return false;
        }
        int end = start + c.size();

        System.arraycopy(items, start, items, 0, end - start);
        int sizeBeforeChange = size;
        size = c.size();
        removeExcessItems(sizeBeforeChange);

        modCount++;
        return true;
    }

    private int getStartIndex(Object[] a) {
        return Collections.indexOfSubList(Arrays.asList(items), Arrays.asList(a));
    }

    private void removeExcessItems(int sizeBeforeChange) {
        int i = size;

        for (; i < sizeBeforeChange; i++) {
            items[i] = null;
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

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

        add(index, item);
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

        System.arraycopy(items, index + 1, items, index, size - index);
        modCount++;
        size--;

        return itemToRemove;
    }

    @Override
    public int indexOf(Object item) {
        if (item == null) {
            for (int i = 0; i < size; i++) {
                if (items[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < items.length; i++) {
                if (item.equals(items[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object item) {
        if (item == null) {
            for (int i = items.length - 1; i >= 0; i--) {
                if (items[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = items.length - 1; i >= 0; i--) {
                if (item.equals(items[i])) {
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
