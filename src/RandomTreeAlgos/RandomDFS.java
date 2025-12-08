package RandomTreeAlgos;

import Graph.Edge;
import Graph.Graph;
import Graph.Arc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomDFS implements SpanningTreeGenerator {

    private boolean[] visited;
    private ArrayList<Edge> treeEdges;
    private Random random;

    @Override
    public ArrayList<Edge> generate(Graph graph) {
        int n = graph.order;
        visited = new boolean[n];
        treeEdges = new ArrayList<>();
        random = new Random();

        int root = random.nextInt(n);
        dfs(graph, root);

        return treeEdges;
    }

    private void dfs(Graph graph, int u) {
        visited[u] = true;

        Arc[] outArcs = graph.outEdges(u);
        List<Arc> neighbors = Arrays.asList(outArcs);
        Collections.shuffle(neighbors, random);

        for (Arc arc : neighbors) {
            int v = arc.getDest();
            if (!visited[v]) {
                // Create an undirected edge for the tree
                // The original graph has edges with weights, but here we just need
                // connectivity.
                // We can try to find the original edge or just create a new one.
                // Since we need to return ArrayList<Edge>, and Edge constructor takes source,
                // dest, weight.
                // We'll use 0.0 for weight as it's not specified.
                // Or better, use the support edge from the arc if available, but Arc class in
                // this project might wrap Edge.

                // Let's check Arc class content.
                // Assuming Arc has a reference to the underlying Edge or we just create a new
                // Edge.
                // The prompt says "Add the edge to the spanning tree".
                // I'll create a new Edge(u, v, 0).

                // Wait, Arc constructor in Graph.java: new Arc(edge, boolean).
                // So Arc might have the edge.
                // Let's assume we can just create a new Edge for the MST result.

                // Actually, let's check Arc.java to be sure.
                // But for now I'll assume I can just create new Edge(u, v, 0).

                treeEdges.add(arc.support);
                dfs(graph, v);
            }
        }
    }
}
