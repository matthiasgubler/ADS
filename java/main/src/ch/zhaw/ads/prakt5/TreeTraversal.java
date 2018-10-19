package ch.zhaw.ads.prakt5;

import sun.misc.Queue;

public class TreeTraversal<T extends Comparable<T>> implements Traversal<T> {

    private TreeNode<T> root;

    public TreeTraversal(TreeNode<T> root) {
        this.root = root;
    }

    @Override
    public void inorder(Visitor<T> visitor) {
        inorder(root, visitor);
    }

    private void inorder(TreeNode<T> node, Visitor<T> visitor) {
        if (node != null) {
            inorder(node.left, visitor);
            visitor.visit(node.element);
            inorder(node.right, visitor);
        }
    }

    @Override
    public void preorder(Visitor<T> visitor) {
        preorder(root, visitor);
    }

    private void preorder(TreeNode<T> node, Visitor<T> visitor) {
        if (node != null) {
            visitor.visit(node.element);
            preorder(node.left, visitor);
            preorder(node.right, visitor);
        }
    }

    @Override
    public void postorder(Visitor<T> visitor) {
        postorder(root, visitor);
    }

    private void postorder(TreeNode<T> node, Visitor<T> visitor) {
        if (node != null) {
            postorder(node.left, visitor);
            postorder(node.right, visitor);
            visitor.visit(node.element);
        }
    }

    @Override
    public void levelorder(Visitor<T> visitor) {
        levelorder(root, visitor);
    }

    @Override
    public void interval(Comparable<T> min, Comparable<T> max, Visitor<T> visitor) {
        interval(root, min, max, visitor);
    }

    private void interval(TreeNode<T> node, Comparable<T> min, Comparable<T> max, Visitor<T> visitor) {
        if (node != null) {
            if (min.compareTo(node.element) > 0) {
                interval(node.right, min, max, visitor);
            } else if (min.compareTo(node.element) < 0) {
                interval(node.left, min, max, visitor);
            } else if (min.compareTo(node.element) <= 0 && max.compareTo(node.element) >= 0) {
                visitor.visit(node.element);
                interval(node.left, min, max, visitor);
                interval(node.right, min, max, visitor);
            }
        }
    }

    private void levelorder(TreeNode<T> node, Visitor<T> visitor) {
        Queue<TreeNode<T>> q = new Queue();
        if (node != null) {
            q.enqueue(node);
        }
        while (!q.isEmpty()) {
            try {
                node = q.dequeue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            visitor.visit(node.element);
            if (node.left != null) {
                q.enqueue(node.left);
            }
            if (node.right != null) {
                q.enqueue(node.right);
            }
        }
    }

}