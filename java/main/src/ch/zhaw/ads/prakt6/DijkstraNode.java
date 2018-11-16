package ch.zhaw.ads.prakt6;

public class DijkstraNode<E> extends Node<E> implements Comparable<DijkstraNode> {
    boolean mark;
    DijkstraNode<E> prev;
    double dist;

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public boolean getMark() {
        return mark;
    }

    public void setMark(boolean m) {
        mark = m;
    }

    public DijkstraNode<E> getPrev() {
        return prev;
    }

    public void setPrev(DijkstraNode<E> p) {
        prev = p;
    }

    public int compareTo(DijkstraNode n) {
        return (int) (dist - n.dist);
    }
}