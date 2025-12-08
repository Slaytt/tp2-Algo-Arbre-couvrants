import Graph.*;
import GraphClasses.*;
import RandomTreeAlgos.*;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    @SuppressWarnings("unused")
    private final static Random gen = new Random();

    static Grid grid = null;

    public static void main(String argv[]) throws InterruptedException {

        // 1. Generate a Grid Graph (20x20)
        grid = new Grid(20, 20);
        Graph graph = grid.graph;
        System.out.println("Graph: Grid 20x20, Order: " + graph.order);

        // 2. Define Algorithms
        SpanningTreeGenerator[] algos = {
                new InsertionAleatoire(),
                new RandomDFS(),
                new AldousBroder(),
                new Wilson()
        };
        String[] names = { "RandomWeightMST", "RandomDFS", "AldousBroder", "Wilson" };

        // 3. Run Experiments
        int noOfSamples = 40;

        for (int i = 0; i < algos.length; i++) {
            System.out.println("\n--- Running " + names[i] + " ---");
            Stats stats = new Stats(noOfSamples);

            for (int j = 0; j < noOfSamples; j++) {
                long start = System.nanoTime();
                ArrayList<Edge> randomTree = algos[i].generate(graph);
                long end = System.nanoTime();
                stats.update(randomTree, end - start);
            }
            stats.print();
        }
    }

    private static class Stats {
        public int nbrOfSamples;
        private int diameterSum = 0;
        private double eccentricitySum = 0;
        private long wienerSum = 0;
        private int degreesSum[] = { 0, 0, 0, 0, 0 };
        private int degrees[];
        private long totalTime = 0;

        public Stats(int noOfSamples) {
            this.nbrOfSamples = noOfSamples;
        }

        public void print() {
            System.out.println("On " + nbrOfSamples + " samples:");
            System.out.println("Average computation time: "
                    + (totalTime / nbrOfSamples / 1_000_000) + "ms");
            System.out.println("Average diameter: "
                    + (diameterSum / nbrOfSamples));
            System.out.println("Average wiener index: "
                    + (wienerSum / nbrOfSamples));
            System.out.println("Average eccentricity: "
                    + (eccentricitySum / nbrOfSamples));
            System.out.println("Average number of leaves: "
                    + (degreesSum[1] / nbrOfSamples));
            System.out.println("Average number of degree 2 vertices: "
                    + (degreesSum[2] / nbrOfSamples));
        }

        public void update(ArrayList<Edge> randomTree, long duration) {
            totalTime += duration;
            RootedTree rooted = new RootedTree(randomTree, 0);
            diameterSum = diameterSum + rooted.getDiameter();
            eccentricitySum = eccentricitySum + rooted.getAverageEccentricity();
            wienerSum = wienerSum + rooted.getWienerIndex();

            degrees = rooted.getDegreeDistribution(4);
            for (int j = 1; j < 5; j++) {
                degreesSum[j] = degreesSum[j] + degrees[j];
            }
        }

    }

}
