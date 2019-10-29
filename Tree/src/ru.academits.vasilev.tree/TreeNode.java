package ru.academits.vasilev.tree;

class TreeNode<T> {
    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;

    TreeNode(T data) {
        this.data = data;
    }

    T getData() {
        return data;
    }

    void setData(T data) {
        this.data = data;
    }

    TreeNode<T> getLeft() {
        return left;
    }

    void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    TreeNode<T> getRight() {
        return right;
    }

    void setRight(TreeNode<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        if (data == null) {
            return "null";
        }

        return data.toString();
    }
}