package org.example;

public class Experiment {

    // Run BFS, DFS and Dijkstra for graph
    public void runTraversals(Graph g) {

        long bfsStart = System.nanoTime();
        g.bfs(0);
        long bfsEnd = System.nanoTime();

        long dfsStart = System.nanoTime();
        g.dfs(0);
        long dfsEnd = System.nanoTime();

        // ===== ADDED =====
        long dijkstraStart = System.nanoTime();
        g.dijkstra(0);
        long dijkstraEnd = System.nanoTime();
        // =================

        System.out.println("BFS Execution Time: "
                + (bfsEnd - bfsStart) + " ns");

        System.out.println("DFS Execution Time: "
                + (dfsEnd - dfsStart) + " ns");

        // ===== ADDED =====
        System.out.println("Dijkstra Execution Time: "
                + (dijkstraEnd - dijkstraStart) + " ns");
        // =================
    }

    // Create graph with selected size
    public Graph createGraph(int vertices) {

        Graph graph = new Graph();

        // Add vertices
        for (int i = 0; i < vertices; i++) {
            graph.addVertex(new Vertex(i));
        }

        // ===== CHANGED =====
        // Add weighted edges
        for (int i = 0; i < vertices - 1; i++) {

            graph.addEdge(i, i + 1, (i + 1) * 2);

            // Extra connections
            if (i + 2 < vertices) {

                graph.addEdge(i,
                        i + 2,
                        (i + 2) * 3);
            }
        }
        // ===================

        return graph;
    }

    // Run tests for different graph sizes
    public void runMultipleTests() {

        int[] sizes = {10, 30, 100};

        for (int size : sizes) {

            System.out.println("\n============================");
            System.out.println("Graph Size: "
                    + size + " vertices");
            System.out.println("============================");

            Graph graph = createGraph(size);

            if (size == 10) {
                graph.printGraph();
            }

            runTraversals(graph);
        }
    }

    public void printResults() {

        System.out.println(
                "\nExperiment completed successfully.");
    }
}

