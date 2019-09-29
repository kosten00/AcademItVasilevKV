package ru.academits.vasilev.tree.main;

import ru.academits.vasilev.tree.BinaryTree;

/**
 * Реализовать бинарное дерево поиска
 * Нужны операции:
 * Вставка
 * Поиск узла
 * Удаление первого вхождения узла по значению
 * Получение числа элементов
 * Обходы в ширину и глубину
 * Обход в глубину двумя вариантами – с рекурсией и без
 */

public class TreeMain {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();

        tree.insert(10);
        tree.insert(11);
        tree.insert(9);
        tree.insert(12);
        tree.insert(8);
        tree.insert(13);
        tree.insert(7);

        tree.breadthTraverse();

        System.out.println("root = " + tree.getRoot());

        System.out.println(tree.searchInDepth(3));
    }
}
