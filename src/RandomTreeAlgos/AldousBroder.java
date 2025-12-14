package RandomTreeAlgos;

import Graph.Edge;
import Graph.Graph;
import Graph.Arc;

import java.util.ArrayList;
import java.util.Random;

public class AldousBroder implements SpanningTreeGenerator {

    @Override
    public ArrayList<Edge> generate(Graph graph) {
        int n = graph.order;
        ArrayList<Edge> treeEdges = new ArrayList<>();
        boolean[] visited = new boolean[n];
        Random random = new Random();

        int current = random.nextInt(n);
        visited[current] = true;
        int visitedCount = 1;

        while (visitedCount < n) {
            Arc[] neighbors = graph.outEdges(current);
            if (neighbors.length == 0)
                break;

            Arc arc = neighbors[random.nextInt(neighbors.length)];
            int neighbor = arc.getDest();

            if (!visited[neighbor]) {
                visited[neighbor] = true;
                treeEdges.add(arc.support);
                visitedCount++;
            }
            current = neighbor;
        }

        return treeEdges;
    }
}
