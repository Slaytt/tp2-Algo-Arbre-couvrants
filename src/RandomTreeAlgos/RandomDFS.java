package RandomTreeAlgos;

import Graph.Edge;
import Graph.Graph;
import Graph.Arc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack; // On a besoin de cette structure

public class RandomDFS implements SpanningTreeGenerator {

    @Override
    public ArrayList<Edge> generate(Graph graph) {
        int n = graph.order;
        boolean[] visited = new boolean[n];
        ArrayList<Edge> treeEdges = new ArrayList<>();
        Random random = new Random();
        Stack<Arc> stack = new Stack<>();

        int root = random.nextInt(n);
        visited[root] = true;

        List<Arc> rootNeighbors = Arrays.asList(graph.outEdges(root));
        Collections.shuffle(rootNeighbors, random);
        for (Arc a : rootNeighbors) {
            stack.push(a);
        }

        while (!stack.isEmpty()) {
            Arc arc = stack.pop();
            int v = arc.getDest();

            if (!visited[v]) {
                visited[v] = true;
                treeEdges.add(arc.support);

                List<Arc> neighbors = Arrays.asList(graph.outEdges(v));
                Collections.shuffle(neighbors, random);

                for (Arc nextArc : neighbors) {
                    if (!visited[nextArc.getDest()]) {
                        stack.push(nextArc);
                    }
                }
            }
        }

        return treeEdges;
    }
}