package ru.academits.vasilev.tree;

class BinaryTree<T extends Comparable<? super T>> {
    private TreeNode<T> root;

    public void insert(T data) {
        TreeNode<T> insertionNode= new TreeNode<>(data);

        if (root == null){
            root = insertionNode;
        } else {
            TreeNode<T> current = root;
            TreeNode<T> parent;

            while (true) {
                parent = current;

                if (insertionNode.data.compareTo(current.data) < 0) {
                    current = current.left;

                    if(current == null) {
                        parent.left = insertionNode;
                        return;
                    }
                }
                else {
                    current = current.right;

                    if(current == null) {
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

    //обход в ширину рекурсией
    //бход в глубину рекрсией и без
}


