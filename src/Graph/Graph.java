package Graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
    // Classe de graphe non orienté permettant de manipuler
    // en même temps des arcs (orientés) pour pouvoir stocker
    // un arbre couvrant, en plus du graphe.

    public int order;
    public int upperBound;
    public int edgeCardinality;

    ArrayList<LinkedList<Edge>> incidency;
    ArrayList<LinkedList<Arc>> inIncidency;
    ArrayList<LinkedList<Arc>> outIncidency;

    // Tableau pour suivre les sommets actifs (suppression/ajout)
    private boolean[] isActive;

    public Graph(int upperBound) {
        this.upperBound = upperBound;
        this.order = upperBound;
        this.edgeCardinality = 0;

        incidency = new ArrayList<>(upperBound);
        inIncidency = new ArrayList<>(upperBound);
        outIncidency = new ArrayList<>(upperBound);
        isActive = new boolean[upperBound];

        for (int i = 0; i < upperBound; i++) {
            incidency.add(new LinkedList<Edge>());
            inIncidency.add(new LinkedList<Arc>());
            outIncidency.add(new LinkedList<Arc>());
            isActive[i] = true; // Par défaut, tous les sommets sont actifs
        }
    }

    public boolean isVertex(int vertex) {
        return vertex >= 0 && vertex < upperBound && isActive[vertex];
    }

    public void addVertex(int vertex) {
        if (vertex >= 0 && vertex < upperBound) {
            isActive[vertex] = true;
        }
    }

    public void deleteVertex(int vertex) {
        if (vertex >= 0 && vertex < upperBound) {
            isActive[vertex] = false;
        }
    }

    public void ensureVertex(int vertex) {
        addVertex(vertex);
    }

    public void addArc(Arc arc) {
        // On s'assure que les sommets existent
        ensureVertex(arc.getSource());
        ensureVertex(arc.getDest());

        outIncidency.get(arc.getSource()).add(arc);
        inIncidency.get(arc.getDest()).add(arc);
    }

    public void addEdge(Edge edge) {
        // On s'assure que les sommets existent
        ensureVertex(edge.source);
        ensureVertex(edge.dest);

        incidency.get(edge.source).add(edge);
        incidency.get(edge.dest).add(edge);
        this.edgeCardinality++;

        Arc arcAvant = new Arc(edge, false);
        Arc arcArriere = new Arc(edge, true);

        outIncidency.get(arcAvant.getSource()).add(arcAvant);
        inIncidency.get(arcAvant.getDest()).add(arcAvant);

        outIncidency.get(arcArriere.getSource()).add(arcArriere);
        inIncidency.get(arcArriere.getDest()).add(arcArriere);
    }

    public Arc[] outEdges(int vertex) {
        // Retourne les arcs sortants seulement si le sommet est actif
        if (!isVertex(vertex))
            return new Arc[0];
        return outIncidency.get(vertex).toArray(new Arc[0]);
    }

    public ArrayList<Edge> getAllEdges() {
        ArrayList<Edge> allEdges = new ArrayList<>();

        for (int u = 0; u < upperBound; u++) {
            if (!isVertex(u))
                continue;
            for (Edge e : incidency.get(u)) {
                // on ajoute l'arête ssi u est la source pour éviter les doublons
                // et on vérifie que la destination est aussi active
                if (e.source == u && isVertex(e.dest)) {
                    allEdges.add(e);
                }
            }
        }
        return allEdges;
    }
}