package ru.academits.vasilev.tree;

class TreeNode<T> {
    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;

    TreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
//        if (data.equals(null)) {
//            return "null";
//        }

        return data.toString();
    }
}