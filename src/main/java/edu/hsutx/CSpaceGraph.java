package edu.hsutx;

import java.util.ArrayList;

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
     * @param cspace a double array of ints, 0 for open space, 1 otherwise
     */
    public CSpaceGraph(int[][] cspace) {
        // TODO - Implement
        super(0, new ArrayList<>());
        boolean above;
        boolean left;
        boolean right;
        boolean below;
        double sqrt2 = Math.sqrt(2);
        for (int i = 0; i <cspace.length; i++) {
            for (int j = 0; j <cspace[0].length; j++) {
                above = i>0;
                below = i<cspace.length-1;
                left = j>0;
                right = j<cspace[i].length-1;

                if (cspace[i][j]==0) {
                    if (above) {
                        if (cspace[i-1][j]==0) {
                            addEdge((i * 300) + j, ((i - 1) * 300) + j, 1);
                        }
                        if (left && cspace[i-1][j-1]==0) {
                            addEdge((i * 300) + j, ((i-1) * 300) + j-1, sqrt2);
                        }
                        if (right && cspace[i-1][j+1]==0) {
                            addEdge((i * 300) + j, ((i - 1) * 300) + (j+1), sqrt2);
                        }
                    }
                    if (left && cspace[i][j-1]==0) {
                        this.addEdge((i * 300) + j, (i * 300) + j-1, 1);
                    }
                    if (right && cspace[i][j+1]==0) {
                        addEdge((i * 300) + j, (i * 300) + j + 1, 1);
                    }
                    if (below) {
                        if (cspace[i+1][j]==0) {
                            addEdge((i * 300) + j, ((i + 1) * 300) + j, 1);
                        }
                        if (left && cspace[i+1][j-1]==0) {
                            addEdge((i * 300) + j, ((i+1) * 300) + j-1, sqrt2);
                        }
                        if (right && cspace[i+1][j+1]==0) {
                            addEdge((i * 300) + j, ((i + 1) * 300) + j+1, sqrt2);
                        }
                    }
                }


            }
        }
    }

    /***
     * Wrapper Class for getDijkstraPath using points for cspaces
     * @param start starting point
     * @param end ending point
     * @return the path, in point form
     */
    public Point[] getDijkstrasPath(Point start, Point end) {
        // convert the int[] result back to Points, and return the result
        // The starter code returns a list of points in a straight line from 0,0 to 299,299
        int startVertex = ((start.getX())*300) + start.getY();
        int endVertex = ((end.getX())*300) + end.getY();
        int [] path = super.getDijkstrasPath(startVertex, endVertex);
        Point [] pointList = new Point [path.length];
        for (int i=0; i<path.length; i++) {
            pointList[i]= new Point(path[i]/300,path[i]%300);
        }
        return pointList;
    }
}
