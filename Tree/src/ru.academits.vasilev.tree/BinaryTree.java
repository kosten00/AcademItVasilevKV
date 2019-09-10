package ru.academits.vasilev.tree;

import java.util.LinkedList;
import java.util.Stack;

public class BinaryTree<T extends Comparable<? super T>> {
    private TreeNode<T> root;

    public TreeNode<T> getRoot() {
        return root;
    }

    private void checkRoot() {
        if (root == null) {
            throw new NullPointerException("tree has no elements");
        }
    }

    public void insert(T data) {
        TreeNode<T> insertionNode = new TreeNode<>(data);

        if (root == null) {
            root = insertionNode;
        } else {
            TreeNode<T> current = root;

            while (true) {
                TreeNode<T> parent = current;

                if (insertionNode.data.compareTo(current.data) < 0) {
                    current = current.left;

                    if (current == null) {
                        parent.left = insertionNode;
                        return;
                    }
                } else {
                    current = current.right;

                    if (current == null) {
                        parent.right = insertionNode;
                        return;
                    }
                }
            }
        }
    }

    public boolean searchInDepth(T data) {
        checkRoot();

        Stack<TreeNode<T>> stack = new Stack<>();

        stack.push(root);
        while (!stack.empty()) {
            TreeNode<T> current = stack.pop();

            if (current.data.compareTo(data) == 0) {
                return true;
            }

            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }
        }

        return false;
    }

    public boolean remove(T data) {
        return false;
    }

    public int getNodeCount(TreeNode<T> treeNode) {
        return -1;
    }

    public void breadthTraverse() {
        LinkedList<TreeNode<T>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.pop();
            System.out.println(node.data);

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }
}

//обход в ширину рекурсией+
//бход в глубину рекрсией и без