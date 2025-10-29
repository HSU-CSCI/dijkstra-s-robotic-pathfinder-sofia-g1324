package edu.hsutx;
import java.util.*;
import java.util.Collections;

public class WeightedDirectedGraph {
    //Adjacency list - list 0 = vertex 1, etc
    List<List<Edge>> vertices;

    /***
     *
     * @param vertexQuantity: Total number of vertices, as an int.  We will start counting at vertex 1, not 0.
     * @param edgeList: an List of Edges containing start and end vertex # and weight.
     ***/
    public WeightedDirectedGraph(int vertexQuantity, List<Edge> edgeList) {
        //Leave vertices[0] as empty and unused, so that when accessing the graph the vertex number matches the index of vertices
        vertices = new ArrayList(vertexQuantity+1);
        for (int i = 0; i<= vertexQuantity+1; i++) vertices.add(new ArrayList<Edge>());
        for (Edge e : edgeList) {
            /*if (vertices.get(e.getStart())== null) {
                vertices.set(e.getStart(), new ArrayList<Edge>());
                vertices.get(e.getStart()).add(e);
            }*/
            if (!vertices.get(e.getStart()).contains(e)) {
                vertices.get(e.getStart()).add(e);
            }
        }
    }

    /***
     * returns true if vertex[start] has an edge to vertex[end], otherwise returns false
     * @param start
     * @param end
     */
    public boolean isAdjacent(int start, int end) {
        for (Edge e : vertices.get(start)) {
            if (e.getEnd() == end) return true;
        }
        return false;
    }

    /***
     * returns a 2d matrix of adjacency weights, with 0 values for non-adjacent vertices.
     * @return matrix of doubles representing adjacent edge weights
     */
    public double[][] adjacencyMatrix() {
        double [][] adjacencyMatrix = new double[vertices.size()][vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            for (int j=0; j<vertices.size();j++) {
                adjacencyMatrix[i][j] = 0.0;
            }
            if (vertices.get(i) != null) {
                for (Edge e : vertices.get(i)) adjacencyMatrix[e.getStart()][e.getEnd()] = e.getWeight();
            }
        }
        return adjacencyMatrix;
    }

    /***
     * Conducts a Breadth First Search and returns the path from start to end, or null if not connected.
     * For accurate testing reproduction, add new vertices to the queue from smallest to largest.
     * @param start
     * @param end
     * @return an array of integers containing the path of vertices to be traveled, including start and end.
     */
    public int[] getBFSPath(int start, int end) {
        Deque<Edge> q = new ArrayDeque<>(vertices.get(start));
        boolean [] visited = new boolean [vertices.size()+1];
        int [] parent = new int [vertices.size()+1];
        visited[start] = true;
        parent[start] = -1;
        while (!q.isEmpty()) {
            Edge e = q.pollFirst();
            if (!visited[e.getEnd()]) {
                if (e.getEnd() == end) {
                    //use ArrayList to add each parent to the beginning and resize?
                    parent[e.getEnd()] = e.getStart();
                    List<Integer> backPath = new ArrayList<>();
                    backPath.add(e.getEnd());
                    int j = e.getEnd();
                    while (parent[j]!=-1) {
                        backPath.add(parent[j]);
                        j = parent[j];
                    }
                    Collections.reverse(backPath);
                    int [] path =  new int[backPath.size()];
                    for (int i = 0; i < backPath.size(); i++) {
                        path[i] = backPath.get(i);
                    }
                    return path;
                }
                q.addAll(vertices.get(e.getEnd()));
                visited[e.getEnd()] = true;
                parent[e.getEnd()] = e.getStart();
            }
        }

        return null;
    }

    /***
     * Conducts a Depth First Search, and returns the path from start to end, or null if not connected.
     * Again, for accurate testing reproduction, add new vertices to the stack from smallest to largest.
     * @param start
     * @param end
     * @return an array of integers containing the path of vertices to be traveled, including start and end.
     */
    public int[] getDFSPath(int start, int end) {
        Deque<Edge> q = new ArrayDeque<>();
        boolean [] visited = new boolean [vertices.size()+1];
        int [] parent = new int [vertices.size()+1];
        visited[start] = true;
        parent[start] = -1;
        for (int i = 0; i<vertices.get(start).size(); i++) q.addFirst(vertices.get(start).get(i));
        while (!q.isEmpty()) {
            Edge e = q.pollFirst();
            if (!visited[e.getEnd()]) {
                if (e.getEnd() == end) {
                    //use ArrayList to add each parent to the beginning and resize?
                    parent[e.getEnd()] = e.getStart();
                    List<Integer> backPath = new ArrayList<>();
                    backPath.add(e.getEnd());
                    int j = e.getEnd();
                    while (parent[j]!=-1) {
                        backPath.add(parent[j]);
                        j = parent[j];
                    }
                    Collections.reverse(backPath);
                    int [] path =  new int[backPath.size()];
                    for (int i = 0; i < backPath.size(); i++) {
                        path[i] = backPath.get(i);
                    }
                    return path;
                }
                visited[e.getEnd()] = true;
                for (int i = 0; i<vertices.get(e.getEnd()).size(); i++) {
                    if (!visited[vertices.get(e.getEnd()).get(i).getEnd()]) q.addFirst(vertices.get(e.getEnd()).get(i));
                }
                parent[e.getEnd()] = e.getStart();
            }
        }
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
        double [] d = new double[vertices.size()+1];
        boolean [] visited = new boolean [vertices.size()+1];
        int [] parent = new int [vertices.size()+1];
        Arrays.fill(d,Integer.MAX_VALUE);
        visited[start] = true;
        parent[start] = -1;
        d[start] = 0;
        for (int i=0; i<vertices.size(); i++) {
            int v = -1;
            //find lowest d value
            for (int j=1; j<=vertices.size();j++) {
                if (!visited[j] && (v == -1 || d[j]<d[v])) {
                    v = j;
                }
            }
            //If hitting an infinite d value (disconnected node) then exit
            if (d[v] == Integer.MAX_VALUE) break;
            //mark as visited
            visited[v] = true;
            //relax all edges, set parent
            for (Edge e : vertices.get(v)) {
                if (d[v]+e.getWeight()<d[e.getEnd()]) {
                    d[e.getEnd()] = d[v]+e.getWeight();
                    parent[e.getEnd()] = v;
                }
            }
        }
        //return the shortest path from start to end
        ArrayList<Integer> revPath = new ArrayList<>();
        int k = end;
        if (d[k]==Integer.MAX_VALUE) return null;
        revPath.add(k);
        while (parent[k]!=-1) {
            revPath.add(parent[k]);
            k = parent[k];
        }
        Collections.reverse(revPath);
        int [] path = new int [revPath.size()];
        for (int i = 0; i < revPath.size(); i++) {
            path[i] = revPath.get(i);
        }
        return path;
    }

    /**
     * Method to add an edge.
     *
     *
     */
    public void addEdge(int start, int end, double weight) {
        Edge e = new Edge(start, end, weight);
        while (vertices.size()< start+1) {
            vertices.add(new ArrayList<>());
        }
        vertices.get(start).add(e);
    }
}

