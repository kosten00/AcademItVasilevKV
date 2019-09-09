package ru.academits.vasilev.tree;

import java.util.LinkedList;

public class BinaryTree<T extends Comparable<? super T>> {
    private TreeNode<T> root;

    public TreeNode<T> getRoot() {
        return root;
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

    public boolean search(T data) {


        return false;
    }

    public boolean remove(T data) {
        return false;
    }

    public int getNodeCount(TreeNode<T> treeNode) {
        return -1;
    }

    public void breadthFirstTraverse() {
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

//обход в ширину рекурсией
//бход в глубину рекрсией и без