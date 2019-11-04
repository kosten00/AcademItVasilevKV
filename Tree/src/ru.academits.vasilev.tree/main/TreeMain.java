package ru.academits.vasilev.tree.main;

import ru.academits.vasilev.tree.BinaryTree;

/*
4. п.3 остался.
Например, getRoot

5. Метод поиска элемента - нужно сделать без алгоритма обхода, это неэффективно.
Обход - это по сути проход по всем элементам дерева.
А для поиска нужного элемента должен быть более эффективный алгоритм
6. Дерево должно нормально работать с null данными
 */

public class TreeMain {
    public static void main(String[] args) {
        BinaryTree<Integer> tree1 = new BinaryTree<>();

        tree1.insert(10);
        tree1.insert(11);
        tree1.insert(9);
        tree1.insert(12);
        tree1.insert(8);
        tree1.insert(13);
        tree1.insert(7);

        System.out.println(tree1.visitInDepth(System.out::println));

        System.out.println();

        System.out.println("removing 7" + tree1.remove(7));

        System.out.println("in depth");

        tree1.visitInDepthRecursively(System.out::println);

        System.out.println("in breadth");

        tree1.visitInBreadth(System.out::println);

        BinaryTree<Integer> tree2 = new BinaryTree<>(new TreeElementsComparator());

        tree2.insert(10);
        tree2.insert(11);
        tree2.insert(9);
        tree2.insert(12);
        tree2.insert(8);
        tree2.insert(13);
        tree2.insert(7);

        System.out.println(tree2.search(13));
        System.out.println(tree2.search(1));
        System.out.println(tree2.search(null));
    }
}