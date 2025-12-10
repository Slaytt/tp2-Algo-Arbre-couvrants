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
        boolean[] visited = new boolean[n]; // On le déclare ici, c'est plus propre
        ArrayList<Edge> treeEdges = new ArrayList<>();
        Random random = new Random();
        Stack<Arc> stack = new Stack<>(); // Notre pile explicite

        // 1. Choisir une racine aléatoire
        int root = random.nextInt(n);
        visited[root] = true;

        // 2. Initialiser la pile avec les voisins de la racine (mélangés)
        List<Arc> rootNeighbors = Arrays.asList(graph.outEdges(root));
        Collections.shuffle(rootNeighbors, random);
        for (Arc a : rootNeighbors) {
            stack.push(a);
        }

        // 3. Boucle principale (remplace la récursion)
        while (!stack.isEmpty()) {
            Arc arc = stack.pop(); // On prend le dernier ajouté
            int v = arc.getDest();

            if (!visited[v]) {
                // Si le sommet n'est pas encore visité, on l'ajoute à l'arbre
                visited[v] = true;
                treeEdges.add(arc.support);

                // Et on ajoute ses voisins à la pile
                List<Arc> neighbors = Arrays.asList(graph.outEdges(v));
                Collections.shuffle(neighbors, random);

                for (Arc nextArc : neighbors) {
                    // Petite optimisation : on n'ajoute pas si le voisin est déjà visité
                    if (!visited[nextArc.getDest()]) {
                        stack.push(nextArc);
                    }
                }
            }
        }

        return treeEdges;
    }
}