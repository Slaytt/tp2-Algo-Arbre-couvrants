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
        int[] next = new int[n]; // Stores the walk: next[u] = v
        Random random = new Random();

        // 1. Pick a random root and add to InTree
        int root = random.nextInt(n);
        inTree.set(root);

        // 2. Iterate over all vertices
        for (int i = 0; i < n; i++) {
            if (inTree.get(i))
                continue;

            int u = i;
            while (!inTree.get(u)) {
                Arc[] neighbors = graph.outEdges(u);
                if (neighbors.length == 0)
                    break; // Should not happen
                Arc arc = neighbors[random.nextInt(neighbors.length)];
                next[u] = arc.getDest();
                u = next[u];
            }

            // 3. Add the path to the tree
            u = i;
            while (!inTree.get(u)) {
                int v = next[u];
                inTree.set(u);

                // Find the edge (u, v) to add to treeEdges
                // We need the Arc object or Edge object.
                // Since we only stored the destination index in 'next', we need to retrieve the
                // edge.
                // We can iterate neighbors of u to find the one pointing to v.
                // Or we could have stored the Arc in 'next' instead of just int.

                // Let's find the edge.
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
