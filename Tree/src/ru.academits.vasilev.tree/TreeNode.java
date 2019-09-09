package ru.academits.vasilev.tree;

class TreeNode<T extends Comparable<? super T>> {
    T data;
    TreeNode<T> left;
    TreeNode<T> right;

    TreeNode(T data) {
        this.data = data;
    }
}