package Graph;

import java.util.ArrayList;
import java.util.LinkedList;


public class Graph {
    // classe de graphe non orientés permettant de manipuler
    // en même temps des arcs (orientés)
    // pour pouvoir stocker un arbre couvrant, en plus du graphe

    public int order;
    public int upperBound;
    int edgeCardinality;

    ArrayList<LinkedList<Edge>> incidency;
    ArrayList<LinkedList<Arc>> inIncidency;
    ArrayList<LinkedList<Arc>> outIncidency;

    public Graph(int upperBound) {
        // Au début, upperBound==order
        // Ensuite, on pourrait retirer des sommets du graphe.
        // Ainsi, on pourrait avoir upperBound > order
        // Cette modification de la classe devient nécessaire
        // si vous implémentez
        // ou l'algorithme de génération d'arbre couvrant
        // par suppression de sommet, ou l'opération de contraction d’arête.
        // Autrement, on pourra asssumer que upperBound==order.

        this.upperBound = upperBound;
        this.order = upperBound;
        this.edgeCardinality = 0;

        incidency = new ArrayList<>(upperBound);
        inIncidency = new ArrayList<>(upperBound);
        outIncidency = new ArrayList<>(upperBound);

        for (int i = 0; i < upperBound; i++) {
            incidency.add(new LinkedList<Edge>());
            inIncidency.add(new LinkedList<Arc>());
            outIncidency.add(new LinkedList<Arc>());
        }

        // à compléter
    }

    public boolean isVertex(int vertex) {
        // Après avori supprimé certains sommets
        // pas tous le sommets numerotés 0,...,n-1 sont 'vivant'.

        // à compléter
        return true;
    }

    public void addVertex(int vertex) {
        // à compléter
    }

    public void deleteVertex(int vertex){
        // à compléter
    }

    public void ensureVertex(int vertex) {
        // Synonime de addVertex ?

        // à compléter
    }

    public void addArc(Arc arc) {
        outIncidency.get(arc.getSource()).add(arc);
        inIncidency.get(arc.getDest()).add(arc);
    }

    public void addEdge(Edge edge) {
        incidency.get(edge.source).add(edge);
        incidency.get(edge.dest).add(edge);
        this.edgeCardinality = this.edgeCardinality + 1;

        Arc arcAvant = new Arc(edge, false);
        Arc arcArriere = new Arc(edge, true);

        outIncidency.get(arcAvant.getSource()).add(arcAvant);
        inIncidency.get(arcAvant.getDest()).add(arcAvant);

        outIncidency.get(arcArriere.getSource()).add(arcArriere);
        inIncidency.get(arcArriere.getDest()).add(arcArriere);


    }

    public Arc[] outEdges(int vertex) {
        // à modifier, si nécessaire

        // Pour la prochaine ligne voir
        // https://www.baeldung.com/java-collection-toarray-methods
        return outIncidency.get(vertex).toArray(new Arc[0]);
   }

   public ArrayList<Edge> getAllEdges() {
       ArrayList<Edge> allEdges = new ArrayList<>();

       for (int u = 0; u < order; u++) {
           for (Edge e : incidency.get(u)) {
               if (e.source == u) { //on ajoute l'arête ssi u est la source pour skip les doublons
                   allEdges.add(e);
               }
           }
       }
       return allEdges;
   }

}
