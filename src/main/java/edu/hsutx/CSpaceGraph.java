package edu.hsutx;

import java.util.ArrayList;
import java.util.List;

public class CSpaceGraph extends WeightedDirectedGraph {

    /***
     * Convert a cspace to weighted, directed graph
     * a cspace is a 2d matrix representing a robot's ability to navigate through a location in physical space.
     * cspace[x][y] represents location x,y, with coordinate 0,0 at the upper left.
     * The value is set to 1 if the space is blocked (the robot cannot safely pass) and 0 if it is not blocked
     * The graph should be created so that each empty (0) spot is converted to a vertex, with edges to each adjacent unblocked spot
     * For edges moving straight left, right, up, down, the weight of the edge should be set to 1.
     * For diagonal edges, the weight of the edge should be set to the square root of 2
     * You will want to add the x,y coordinates to your vertex data
     * @param cspace
     */
    public CSpaceGraph(int[][] cspace) {
        // TODO - Implement
        super(0, new ArrayList<Edge>());


    }

    /***
     * Wrapper Class for getDykstraPath using points for cspaces
     * @param start
     * @param end
     * @return
     */
    public Point[] getDijkstrasPath(Point start, Point end) {
        // TODO - convert start and end to int vertexes and call the base getDijkstrasPath method
        // convert the int[] result back to Points, and return the result
        // The starter code returns a list of points in a straight line from 0,0 to 299,299
        Point[] pointList = new Point[300];
        for (int i=0; i<300; i++) pointList[i]=new Point(i,i);
        return pointList;
    }
}
