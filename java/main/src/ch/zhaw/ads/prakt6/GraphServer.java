package ch.zhaw.ads.prakt6;

import ch.zhaw.ads.CommandExecutor;

import java.util.StringTokenizer;

import static ch.zhaw.ads.prakt6.DijkstrasAglorithm.calculatePaths;

public class GraphServer implements CommandExecutor {

    public String execute(String fileContent) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        Graph<DijkstraNode, Edge> graph = readInputFile(fileContent);

        DijkstraNode<Edge> start = graph.findNode("Winterthur");

        calculatePaths(start);

        DijkstraNode<Edge> destination = graph.findNode("Lugano");
        do {
            stringBuffer.append("City: " + destination.getName() + "; Distance: " + destination.getDist() + '\n');
            destination = destination.getPrev();
        }
        while (destination != null);

        return stringBuffer.toString();
    }

    private Graph<DijkstraNode, Edge> readInputFile(String fileContent) throws Exception {
        Graph<DijkstraNode, Edge> graph = new AdjListGraph<>(DijkstraNode.class, Edge.class);
        StringTokenizer tok = new StringTokenizer(fileContent);
        while (tok.hasMoreTokens()) {
            String from = tok.nextToken();
            String to = tok.nextToken();
            int distance = Integer.parseInt(tok.nextToken());
            try {
                graph.addEdge(from, to, distance);
                graph.addEdge(to, from, distance);
            } catch (Throwable t) {
                throw new Exception(t);
            }
        }
        return graph;
    }
}