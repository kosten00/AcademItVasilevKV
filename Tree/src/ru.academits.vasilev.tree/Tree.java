package ru.academits.vasilev.tree;

import java.util.Comparator;

class Tree<T> {
    public static class TreeNode<T> {
        private T data;
        private TreeNode<T> left;
        private TreeNode<T> right;

        public T getData() {
            return data;
        }
    }

    private TreeNode<T> root;

    interface Comparable extends Comparator<TreeNode> {
        int compareTo(TreeNode t);

    }
}


