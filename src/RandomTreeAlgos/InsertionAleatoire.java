package RandomTreeAlgos;

import Graph.Edge;
import Graph.Graph;
import AlgoGraph.UnionFind;

import java.util.ArrayList;
import java.util.Collections;

public class InsertionAleatoire implements SpanningTreeGenerator {

    @Override
    public ArrayList<Edge> generate(Graph graph) {
        int n = graph.order;
        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<Edge> allEdges = graph.getAllEdges();
        Collections.shuffle(allEdges);
        UnionFind uf = new UnionFind(n);

        for (Edge e : allEdges) {
            // Si union retourne true, c'est que u et v n'étaient pas connectés.
            // On peut ajouter l'arête sans créer de cycle.
            if (uf.union(e.getSource(), e.getDest())) {
                edges.add(e);
            }
            if (edges.size() == n - 1) {
                break;
            }
        }
        return edges;
    }
}
