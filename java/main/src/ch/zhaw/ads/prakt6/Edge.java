package ch.zhaw.ads.prakt6;

public class Edge<N> {
    protected N dest;  // Zielknoten der Kante
    protected double weight;  // Kantengewicht

    public Edge() {
    }

    public Edge(N dest, double weight) {
        this.dest = dest;
        this.weight = weight;
    }

    public N getDest() {
        return dest;
    }

    public void setDest(N node) {
        this.dest = node;
    }

    double getWeight() {
        return weight;
    }

    public void setWeight(double w) {
        this.weight = w;
    }
}