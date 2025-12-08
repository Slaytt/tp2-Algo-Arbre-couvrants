package AlgoGraph;

//utilisation de union find pour trouver les cycles dans un graphe (pour algorithme de kruskal) inspiré de https://algs4.cs.princeton.edu/15uf/ le weighted union find
public class UnionFind {
    private int[] parent;
    private int[] rang;
    private int nbSets;

    public UnionFind(int n) {
        parent = new int[n];
        rang = new int[n];
        nbSets = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rang[i] = 1;
        }
    }

    public int find(int p) {
        if (parent[p] != p) parent[p] = find(parent[p]);
        return parent[p];
    }

    public boolean union(int i, int j) {
        int racineI = find(i);
        int racineJ = find(j);

        if (racineI == racineJ) {
            return false;
        }

        if (rang[racineI] < rang[racineJ]) {
            parent[racineI] = racineJ;
        }
        else if (rang[racineI] > rang[racineJ]) {
            parent[racineJ] = racineI;
        }
        else {
            parent[racineI] = racineJ;
            rang[racineJ]++;
        }

        nbSets--;
        return true;
    }

    public boolean connected(int i, int j) {
        return find(i) == find(j);
    }

    public int getNbSets() {
        return nbSets;
    }


}
