package ch.zhaw.ads.prakt6;

import java.util.PriorityQueue;
import java.util.Queue;

public class DijkstrasAglorithm {

    public static void calculatePaths(DijkstraNode<Edge> start) {
        Queue<DijkstraNode> priorityQueue = new PriorityQueue<>();
        start.setDist(0);
        priorityQueue.add(start);
        while (!priorityQueue.isEmpty()) {
            DijkstraNode<Edge> currentTown = priorityQueue.remove();
            currentTown.setMark(true);
            for (Edge road : currentTown.getEdges()) {
                DijkstraNode town = (DijkstraNode) road.getDest();
                if (!town.getMark()) {
                    double dist = currentTown.getDist() + road.getWeight();
                    if (town.getPrev() == null || dist < town.getDist()) {
                        town.setDist(dist);
                        town.setPrev(currentTown);
                        priorityQueue.add(town);
                    }
                }
            }
        }
    }
}
