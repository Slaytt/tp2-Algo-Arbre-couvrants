package RandomTreeAlgos;

import Graph.Edge;
import Graph.Graph;
import Graph.Arc;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

public class Wilson implements SpanningTreeGenerator {

    @Override
    public ArrayList<Edge> generate(Graph graph) {
        int n = graph.order;
        ArrayList<Edge> treeEdges = new ArrayList<>();
        BitSet inTree = new BitSet(n);
        int[] next = new int[n];
        Random random = new Random();

        int root = random.nextInt(n);
        inTree.set(root);

        for (int i = 0; i < n; i++) {
            if (inTree.get(i))
                continue;

            int u = i;
            while (!inTree.get(u)) {
                Arc[] neighbors = graph.outEdges(u);
                if (neighbors.length == 0)
                    break;
                Arc arc = neighbors[random.nextInt(neighbors.length)];
                next[u] = arc.getDest();
                u = next[u];
            }

            u = i;
            while (!inTree.get(u)) {
                int v = next[u];
                inTree.set(u);

                Arc[] neighbors = graph.outEdges(u);
                for (Arc arc : neighbors) {
                    if (arc.getDest() == v) {
                        treeEdges.add(arc.support);
                        break;
                    }
                }

                u = v;
            }
        }

        return treeEdges;
    }
}
