package org.example;

public class Edge {

    private Vertex source;
    private Vertex destination;

    // ===== ADDED =====
    private int weight;
    // =================

    // ===== CHANGED CONSTRUCTOR =====
    public Edge(Vertex source, Vertex destination, int weight) {
        this.source = source;
        this.destination = destination;

        // ===== ADDED =====
        this.weight = weight;
        // =================
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }

    // ===== ADDED =====
    public int getWeight() {
        return weight;
    }
    // =================

    @Override
    public String toString() {

        // ===== CHANGED =====
        return source.getId() +
                " -> " +
                destination.getId() +
                " (weight: " + weight + ")";
        // ===================
    }
}