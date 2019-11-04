package ru.academits.vasilev.tree;

import com.sun.source.tree.Tree;

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

    private int getComparision(T data1, T data2) {
        if (comparator == null) {
            Comparable<T> dataToComparable1 = (Comparable<T>) data1;

            return dataToComparable1.compareTo(data2);
        }

        return comparator.compare(data1, data2);
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

        if (getComparision(nodeToRemove.getRight().getData(), successor.getData()) != 0) {
            successorParent.setLeft(successor.getRight());
            successor.setRight(nodeToRemove.getRight());
        }

        return successor;
    }

    public T getRoot() {
        return root.getData();
    }

    public int getElementsCount() {
        return elementsCount;
    }

    public boolean insert(T data) {
        if (data == null) {
            return false;
        }

        TreeNode<T> insertionNode = new TreeNode<>(data);

        if (root == null) {
            root = insertionNode;
        } else {
            TreeNode<T> current = root;

            while (true) {
                TreeNode<T> parent = current;

                if (getComparision(insertionNode.getData(), current.getData()) < 0) {
                    current = current.getLeft();

                    if (current == null) {
                        parent.setLeft(insertionNode);

                        elementsCount++;
                        return true;
                    }
                } else {
                    current = current.getRight();

                    if (current == null) {
                        parent.setRight(insertionNode);

                        elementsCount++;
                        return true;
                    }
                }
            }
        }

        elementsCount++;
        return true;
    }

    public boolean search(T data) {
        if (elementsCount == 0 || data == null) {
            return false;
        }

        TreeNode<T> current = root;

        while (true) {
            if (getComparision(current.getData(), data) == 0) {
                return true;
            }

            if (getComparision(data, current.getData()) < 0) {
                current = current.getLeft();

                if (current == null) {
                    return false;
                }
            } else {
                current = current.getRight();

                if (current == null) {
                    return false;
                }
            }
        }
    }

    public boolean visitInDepth(Consumer<? super T> action) {
        if (elementsCount == 0) {
            return false;
        }

        Stack<TreeNode<T>> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode<T> current = stack.pop();

            action.accept(current.getData());

            if (current.getRight() != null) {
                stack.push(current.getRight());
            }

            if (current.getLeft() != null) {
                stack.push(current.getLeft());
            }
        }

        return false;
    }

    public void visitInDepthRecursively(Consumer<? super T> action) {
        if (action == null) {
            throw new NullPointerException("Consumer == null");
        }

        if (elementsCount == 0) {
            return;
        }

        visitNodeRecursively(root, action);
    }

    public boolean remove(T data) {
        if (elementsCount == 0 || data == null) {
            return false;
        }

        TreeNode<T> current = root;
        TreeNode<T> parent = root;

        boolean isLeftChild = true;

        while (getComparision(current.getData(), data) != 0) {
            parent = current;

            if (getComparision(current.getData(), data) > 0) {
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