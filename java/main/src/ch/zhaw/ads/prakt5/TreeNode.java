package ch.zhaw.ads.prakt5;

class TreeNode<T extends Comparable<T>> {

    T element;

    TreeNode left, right;

    int height;

    int count;

    TreeNode(T element) {
        this.element = element;
        this.count = 1;
        this.height = 1;
    }

    public TreeNode(T element, TreeNode left, TreeNode right) {
        this(element);
        this.left = left;
        this.right = right;
    }

    public T getValue() {
        return element;
    }
}