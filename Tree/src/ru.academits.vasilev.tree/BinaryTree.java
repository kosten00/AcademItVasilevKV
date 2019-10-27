package ru.academits.vasilev.tree;

import java.util.LinkedList;
import java.util.Stack;

/*
1. T должен быть без ограничений на extends.
У класса дерева должно быть 2 конструктора - с компаратором и без.
Если его передали, то для сравнения используется он.
Если нет, то для сравнения пытаемся приводить данные к Comparable<T> и вызывать метод оттуда
2. TreeNode:
- нужно сделать геттеры и сеттеры для полей.
Не должно быть прямого доступа к полям
- toString может упасть с null
3. Из публичных методов дерева не должен быть доступен класс узла, т.к. это деталь реализации
4. Должен быть метод для получения размера дерева.
Он должен быть сделан без обхода, лучше всего хранить размер в поле
5. Обходы должны выполнять полезную работу.
Для этого они должны принимать Consumer<T> и вызывать его метод для данных узлов
6. Публичные методы обходов не должны принимать узлы.
Они должны сами начинать с корня
7. Должны быть 3 отдельных метода для обходов
8. remove и обходы не должны падать для пустого дерева
9. remove - одинаковый compareTo делается дважды за итерацию цикла
 */

public class BinaryTree<T> {
    private TreeNode<T> root;

    public int getElementsCount() {
        return elementsCount;
    }

    private int elementsCount;

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

//                if (insertionNode.data.compareTo(current.data) < 0) {
//                    current = current.left;
//
//                    if (current == null) {
//                        parent.left = insertionNode;
//                        return;
//                    }
//                } else {
//                    current = current.right;
//
//                    if (current == null) {
//                        parent.right = insertionNode;
//                        return;
//                    }
//                }
            }
        }

        elementsCount++;
    }

    public boolean searchInDepth(T data) {
        checkRoot();

        Stack<TreeNode<T>> stack = new Stack<>();

        stack.push(root);
        while (!stack.empty()) {
            TreeNode<T> current = stack.pop();

//            if (current.data.compareTo(data) == 0) {
//                return true;
//            }

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

//        while (current.data.compareTo(data) != 0) {
//            parent = current;
//
//            if (current.data.compareTo(data) > 0) {
//                isLeftChild = true;
//                current = current.left;
//            } else {
//                isLeftChild = false;
//                current = current.right;
//            }
//
//            if (current == null) {
//                return false;
//            }
//        }

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