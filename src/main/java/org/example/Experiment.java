package org.example;

public class Experiment {

    // Run BFS and DFS for graph
    public void runTraversals(Graph g) {

        long bfsStart = System.nanoTime();
        g.bfs(0);
        long bfsEnd = System.nanoTime();

        long dfsStart = System.nanoTime();
        g.dfs(0);
        long dfsEnd = System.nanoTime();

        System.out.println("BFS Execution Time: " + (bfsEnd - bfsStart) + " ns");
        System.out.println("DFS Execution Time: " + (dfsEnd - dfsStart) + " ns");
    }

    // Create graph with selected size
    public Graph createGraph(int vertices) {

        Graph graph = new Graph();

        // Add vertices
        for (int i = 0; i < vertices; i++) {
            graph.addVertex(new Vertex(i));
        }

        // Add edges
        for (int i = 0; i < vertices - 1; i++) {
            graph.addEdge(i, i + 1);

            // Extra connections for more realistic graph
            if (i + 2 < vertices) {
                graph.addEdge(i, i + 2);
            }
        }

        return graph;
    }

    // Run tests for different graph sizes
    public void runMultipleTests() {

        int[] sizes = {10, 30, 100};

        for (int size : sizes) {

            System.out.println("\n============================");
            System.out.println("Graph Size: " + size + " vertices");
            System.out.println("============================");

            Graph graph = createGraph(size);

            if (size == 10) {
                graph.printGraph();
            }

            runTraversals(graph);
        }
    }

    public void printResults() {
        System.out.println("\nExperiment completed successfully.");
    }
}

