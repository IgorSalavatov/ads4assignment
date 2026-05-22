package org.example;

import java.util.*;

public class Graph {

    // ===== CHANGED =====
    // Adjacency list with weighted edges
    private Map<Integer, List<Edge>> adjacencyList;
    // ===================

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    // Add vertex to graph
    public void addVertex(Vertex v) {
        adjacencyList.putIfAbsent(v.getId(), new ArrayList<>());
    }

    // ===== CHANGED METHOD =====
    // Add weighted edge between vertices
    public void addEdge(int from, int to, int weight) {

        adjacencyList.putIfAbsent(from, new ArrayList<>());
        adjacencyList.putIfAbsent(to, new ArrayList<>());

        Vertex source = new Vertex(from);
        Vertex destination = new Vertex(to);

        adjacencyList.get(from)
                .add(new Edge(source, destination, weight));

        // Undirected graph
        adjacencyList.get(to)
                .add(new Edge(destination, source, weight));
    }
    // ==========================


    // Print graph structure
    public void printGraph() {

        System.out.println("Graph structure:");

        for (int vertex : adjacencyList.keySet()) {

            System.out.print(vertex + " -> ");

            // ===== CHANGED =====
            for (Edge edge : adjacencyList.get(vertex)) {

                System.out.print(
                        edge.getDestination().getId()
                                + "(w:" + edge.getWeight() + ") "
                );
            }
            // ===================

            System.out.println();
        }
    }

    // Breadth-First Search
    public void bfs(int start) {

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.offer(start);

        System.out.print("BFS Traversal: ");

        while (!queue.isEmpty()) {

            int current = queue.poll();

            System.out.print(current + " ");

            // ===== CHANGED =====
            for (Edge edge : adjacencyList.get(current)) {

                int neighbor =
                        edge.getDestination().getId();

                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
            // ===================
        }

        System.out.println();
    }

    // Depth-First Search
    public void dfs(int start) {

        Set<Integer> visited = new HashSet<>();

        System.out.print("DFS Traversal: ");

        dfsHelper(start, visited);

        System.out.println();
    }

    // Recursive DFS helper
    private void dfsHelper(int current,
                           Set<Integer> visited) {

        visited.add(current);

        System.out.print(current + " ");

        // ===== CHANGED =====
        for (Edge edge : adjacencyList.get(current)) {

            int neighbor =
                    edge.getDestination().getId();

            if (!visited.contains(neighbor)) {

                dfsHelper(neighbor, visited);
            }
        }
        // ===================
    }

    // ===== ADDED DIJKSTRA =====
    public void dijkstra(int start) {

        Map<Integer, Integer> distance =
                new HashMap<>();

        Set<Integer> visited =
                new HashSet<>();

        for (int vertex : adjacencyList.keySet()) {

            distance.put(vertex,
                    Integer.MAX_VALUE);
        }

        distance.put(start, 0);

        for (int i = 0;
             i < adjacencyList.size();
             i++) {

            int current = -1;

            int minDistance =
                    Integer.MAX_VALUE;

            for (int vertex :
                    adjacencyList.keySet()) {

                if (!visited.contains(vertex)
                        && distance.get(vertex)
                        < minDistance) {

                    minDistance =
                            distance.get(vertex);

                    current = vertex;
                }
            }

            if (current == -1) {
                break;
            }

            visited.add(current);

            for (Edge edge :
                    adjacencyList.get(current)) {

                int neighbor =
                        edge.getDestination()
                                .getId();

                int newDistance =
                        distance.get(current)
                                + edge.getWeight();

                if (newDistance
                        < distance.get(neighbor)) {

                    distance.put(neighbor,
                            newDistance);
                }
            }
        }

        System.out.println(
                "Dijkstra shortest paths:");

        for (int vertex :
                distance.keySet()) {

            System.out.println(
                    "From " + start +
                            " to " + vertex +
                            " = " +
                            distance.get(vertex)
            );
        }
    }
    // =========================
}