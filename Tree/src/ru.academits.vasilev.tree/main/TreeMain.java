package ru.academits.vasilev.tree.main;

import ru.academits.vasilev.tree.BinaryTree;

public class TreeMain {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>(new TreeElementsComparator<Integer>());

        tree.insert(10);
        tree.insert(11);
        tree.insert(9);
        tree.insert(12);
        tree.insert(8);
        tree.insert(13);
        tree.insert(7);

        System.out.println(tree.searchInDepth(1));

        System.out.println();

        System.out.println(tree.remove(7));

        tree.forEach(p -> System.out.println(p.toString()));
    }
}
