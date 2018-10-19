package ch.zhaw.ads.prakt5;

/* interface of Traversal ADT */
public interface Traversal<T> {
    /* traverse elements of tree in preorder */
    void preorder(Visitor<T> visitor);

    /* traverse elements of tree in inorder */
    void inorder(Visitor<T> visitor);

    /* traverse elements of tree in postorder */
    void postorder(Visitor<T> visitor);

    /* traverse elements of tree in levelorder */
    void levelorder(Visitor<T> visitor);

    void interval(Comparable<T> min, Comparable<T> max, Visitor<T> v);
}
