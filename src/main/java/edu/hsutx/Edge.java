package edu.hsutx;

class Edge {
    private int start;
    private int end;
    private double weight;

    public Edge(int start, int end) {
        this.start = start;
        this.end = end;
        this.weight=1.0;
    }

    public Edge(int start, int end, double weight) {
        this.start = start;
        this.end = end;
        this.weight=weight;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Edge: " + start + " -> " + end;
    }
}
