package edu.hsutx;

import java.util.List;

public class WeightedDirectedGraph {
    // TODO - Implement storage of the graph, including either adjacency list, adjacency matrix, or both.

    /***
     *
     * @param vertexQuantity: Total number of vertices, as an int.  We will start counting at vertex 1, not 0.
     * @param edgeList: an List of Edges containing start and end vertex # and weight.
     ***/
    public WeightedDirectedGraph(int vertexQuantity, List<Edge> edgeList) {
        // TODO - Implement

    }

    /***
     * returns true if vertex[start] has an edge to vertex[end], otherwise returns false
     * @param start
     * @param end
     */
    public static boolean isAdjacent(int start, int end) {
        // TODO - Implement
        return true;
    }

    /***
     * returns a 2d matrix of adjacency weights, with 0 values for non-adjacent vertices.
     * @return matrix of doubles representing adjacent edge weights
     */
    public static double[][] adjacencyMatrix() {
        // TODO - Implement
        return null;
    }

    /***
     * Conducts a Breadth First Search and returns the path from start to end, or null if not connected.
     * For accurate testing reproduction, add new vertices to the queue from smallest to largest.
     * @param start
     * @param end
     * @return an array of integers containing the path of vertices to be traveled, including start and end.
     */
    public static int[] getBFSPath(int start, int end) {
        // TODO - Implement
        return null;
    }

    /***
     * Conducts a Depth First Search, and returns the path from start to end, or null if not connected.
     * Again, for accurate testing reproduction, add new vertices to the stack from smallest to largest.
     * @param start
     * @param end
     * @return an array of integers containing the path of vertices to be traveled, including start and end.
     */
    public static int[] getDFSPath(int start, int end) {
        // TODO - Implement
        return null;
    }

    /***
     * Returns a list of vertices in order of traversal for the shortest path generated using Dykstra's Algorithm
     * @param start
     * @param end
     * @return
     */
    public int[] getDijkstrasPath(int start, int end) {
        // TODO - Implement Dijkstra's Algorithm
        // Note
        return null;
    }



}

