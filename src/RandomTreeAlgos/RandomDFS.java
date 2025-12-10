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
                treeEdges.add(arc.support);
                dfs(graph, v);
            }
        }
    }
}
