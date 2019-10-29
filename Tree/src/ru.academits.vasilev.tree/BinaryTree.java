package ru.academits.vasilev.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.function.Consumer;

public class BinaryTree<T> {
    private TreeNode<T> root;
    private int elementsCount;
    private Comparator<T> comparator;

    public BinaryTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public BinaryTree() {
    }

    private boolean isLess(T data1, T data2) {
        if (comparator == null) {
            Comparable<T> dataToComparable1 = (Comparable<T>) data1;
            Comparable<T> dataToComparable2 = (Comparable<T>) data2;

            return dataToComparable1.compareTo((T) dataToComparable2) < 0;
        }

        return comparator.compare(data1, data2) < 0;
    }

    private boolean isGreater(T data1, T data2) {
        if (comparator == null) {
            Comparable<T> dataToComparable1 = (Comparable<T>) data1;
            Comparable<T> dataToComparable2 = (Comparable<T>) data2;

            return dataToComparable1.compareTo((T) dataToComparable2) > 0;
        }

        return comparator.compare(data1, data2) > 0;
    }

    private void visitNodeRecursively(TreeNode<T> node, Consumer<? super T> action) {
        action.accept(node.getData());

        if (node.getLeft() != null) {
            visitNodeRecursively(node.getLeft(), action);
        }

        if (node.getRight() != null) {
            visitNodeRecursively(node.getRight(), action);
        }
    }

    private TreeNode<T> getSuccessor(TreeNode<T> nodeToRemove) {
        TreeNode<T> successorParent = nodeToRemove;
        TreeNode<T> successor = nodeToRemove;
        TreeNode<T> current = nodeToRemove.getRight();

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeft();
        }

        if (!nodeToRemove.getRight().equals(successor)) {
            successorParent.setLeft(successor.getRight());
            successor.setRight(nodeToRemove.getRight());
        }

        return successor;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public int getElementsCount() {
        return elementsCount;
    }

    public void insert(T data) {
        TreeNode<T> insertionNode = new TreeNode<>(data);

        if (root == null) {
            root = insertionNode;
        } else {
            TreeNode<T> current = root;

            while (true) {
                TreeNode<T> parent = current;

                if (isLess(insertionNode.getData(), current.getData())) {
                    current = current.getLeft();

                    if (current == null) {
                        parent.setLeft(insertionNode);
                        return;
                    }
                } else {
                    current = current.getRight();

                    if (current == null) {
                        parent.setRight(insertionNode);
                        return;
                    }
                }
            }
        }

        elementsCount++;
    }

    public boolean searchInDepth(T data) {
        if (elementsCount == 0) {
            return false;
        }

        Stack<TreeNode<T>> stack = new Stack<>();

        stack.push(root);
        while (!stack.empty()) {
            TreeNode<T> current = stack.pop();

            if (current.getData().equals(data)) {
                return true;
            }

            if (current.getRight() != null) {
                stack.push(current.getRight());
            }

            if (current.getLeft() != null) {
                stack.push(current.getLeft());
            }
        }

        return false;
    }

    public void visitInDepth(Consumer<? super T> action) {
        if (action == null) {
            throw new NullPointerException("Consumer == null");
        }

        if (elementsCount == 0) {
            return;
        }

        visitNodeRecursively(root, action);
    }

    public boolean remove(T data) {
        if (elementsCount == 0) {
            return false;
        }

        TreeNode<T> current = root;
        TreeNode<T> parent = root;

        boolean isLeftChild = true;

        while (!current.getData().equals(data)) {
            parent = current;

            if (isGreater(current.getData(), data)) {
                isLeftChild = true;
                current = current.getLeft();
            } else {
                isLeftChild = false;
                current = current.getRight();
            }

            if (current == null) {
                return false;
            }
        }

        if (current.getLeft() == null && current.getRight() == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else if (current.getRight() == null) {
            if (current == root) {
                root = current.getLeft();
            } else if (isLeftChild) {
                parent.setLeft(current.getLeft());
            } else {
                parent.setRight(current.getLeft());
            }
        } else if (current.getLeft() == null) {
            if (current == root) {
                root = current.getRight();
            } else if (isLeftChild) {
                parent.setLeft(current.getRight());
            } else {
                parent.setRight(current.getRight());
            }
        } else {
            TreeNode<T> successor = getSuccessor(current);

            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.setLeft(successor);
            } else {
                parent.setRight(successor);
            }

            successor.setLeft(current.getLeft());
        }

        elementsCount--;
        return true;
    }

    public void visitInBreadth(Consumer<? super T> action) {
        if (elementsCount == 0) {
            return;
        }

        if (action == null) {
            throw new NullPointerException("Consumer == null");
        }

        LinkedList<TreeNode<T>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.pop();

            T t = node.getData();
            action.accept(t);

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }
}