package ch.zhaw.ads.prakt7;

import ch.zhaw.ads.prakt6.DijkstraNode;
import ch.zhaw.ads.prakt6.Edge;
import ch.zhaw.ads.prakt6.Graph;
import ch.zhaw.ads.CommandExecutor;
import ch.zhaw.ads.prakt6.AdjListGraph;
import java.util.StringTokenizer;

import java.awt.*;


public class LabyrinthServer implements CommandExecutor {

    final double SCALE = 10;
    private ServerGraphics graphic = null;

    private void drawPath(String from, String
            to, boolean mouse) {
        double xh0 = from.charAt(0) - '0';
        double yh0 = from.charAt(2) - '0';
        double xh1 = to.charAt(0) - '0';
        double yh1 = to.charAt(2) - '0';
        double x0 = Math.min(xh0,xh1)/SCALE;
        double y0 = Math.min(yh0,yh1)/SCALE;
        double x1 = Math.max(xh0,xh1)/SCALE;
        double y1 = Math.max(yh0,yh1)/SCALE;
        double w = 1/SCALE;
        if (mouse) graphic.drawLine(x0+w/2,y0+w/2,x1+w/2,y1+w/2);
        else {
            if (y0 == y1)
                graphic.fillRect(x0,y0,x1-x0+w,w);
            else
                graphic.fillRect(x0,y0,w,y1-y0+w);
        }
    }

    private boolean findNode(DijkstraNode<Edge> source, DijkstraNode<Edge> target) {
        source.setMark(true);
        if (source == target) {
            return true;
        }
        for (Edge<DijkstraNode> e : source.getEdges()) {
            DijkstraNode<Edge> dest = e.getDest();
            if (dest != null && !dest.getMark()) {
                dest.setPrev(source);
                if (findNode(dest, target)) {
                    return true;
                }
            }
        }
        source.setMark(false);
        return false;
    }

    private void initGraphic() {
        graphic = new ServerGraphics();
        graphic.fillRect(0, 0, 1, 1);
        graphic.setColor(Color.black);

        graphic.setColor(Color.white);
    }

    private Graph<DijkstraNode, Edge> createGraph(StringTokenizer tokenizer) throws Exception {
        Graph<DijkstraNode, Edge> graph
                = new AdjListGraph<>(DijkstraNode.class, Edge.class);
        while (tokenizer.hasMoreTokens()) {
            String from = tokenizer.nextToken();
            String to = tokenizer.nextToken();
            try {
                graph.addEdge(from, to, 1);
                graph.addEdge(to, from, 1);
            } catch (Throwable throwable) {
                throw new Exception(throwable);
            }
            drawPath(from, to, false);
        }
        return graph;
    }

    @Override
    public String execute(String s) throws Exception {
        initGraphic();
        Graph<DijkstraNode, Edge> graph = createGraph(new StringTokenizer(s, " \n"));

        DijkstraNode<Edge> source = graph.findNode("0-6");
        DijkstraNode<Edge> target = graph.findNode("3-0");
        if (findNode(source, target)) {
            graphic.setColor(Color.red);
            while(target.getPrev() != null) {
                drawPath(target.getName(), target.getPrev().getName(), true);
                target = target.getPrev();
            }
        }
        return graphic.getTrace();
    }

}