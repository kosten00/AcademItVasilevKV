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

    private TreeNode<T> getSuccessor(TreeNode<T> nodeToRemove) {
        TreeNode<T> successorParent = nodeToRemove;
        TreeNode<T> successor = nodeToRemove;
        TreeNode<T> current = nodeToRemove.right;

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }

        if (nodeToRemove.right != successor) {
            successorParent.left = successor.right;
            successor.right = nodeToRemove.right;
        }

        return successor;
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

    public void visitInDepthRecursive(TreeNode<T> root) {
        checkRoot();

        System.out.println(root);

        if (root.left != null) {
            visitInDepthRecursive(root.left);
        }

        if (root.right != null) {
            visitInDepthRecursive(root.right);
        }
    }

    public boolean remove(T data) {
        checkRoot();

        TreeNode<T> current = root;
        TreeNode<T> parent = root;

        boolean isLeftChild = true;

        while (current.data.compareTo(data) != 0) {
            parent = current;

            if (current.data.compareTo(data) < 0) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }

            if (current == null) {
                return false;
            }
        }

        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {
            TreeNode<T> successor = getSuccessor(current);

            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }

            successor.left = current.left;
        }

        return true;
    }

    public int getNodeCountBreadthTraverse() {
        LinkedList<TreeNode<T>> queue = new LinkedList<>();

        queue.add(root);

        int nodesCount = 1;

        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.pop();

            if (node.left != null) {
                queue.add(node.left);
                nodesCount++;
            }

            if (node.right != null) {
                queue.add(node.right);
                nodesCount++;
            }
        }

        return nodesCount;
    }
}