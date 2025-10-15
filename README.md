Welcome to Dijkstra's Robotic Pathfinder!

This is an assignment for Hardin-Simmons CSCI-3323 Data Structures Course.

In this assignment, we will extend the class we created last week in the GraphBasics assignment, to handle graphs using Configuration Space (cspace).  

Configuration Space is a grid based mapping of coordinates which are navigable to a robot.  We will represent this by an array of ints, with 0 representing empty navigable space, and 1 representing blocked space.

The assignment has two parts:

1) Create a constructor class that will accept an array, and convert it into vertices and edges.  Each empty space in the array should become a vertex, with an edge connecting to each adjacent empty space.  These edges should have weight 1 for up/down/left/right, or square root of 2 for diagonal.

2) Implement Dijkstra's Algorithm to find the shortest path between two points.  The method will need to return a list of coordinates (using the included Point class), so your data structures will need to be planned out to accomodate this.

The starter code includes a small application (PathFinder.java) which will read a csv from the data directory, call your constructor, call your Dijkstra method, and then generate an image file showing the cspace with the Dijkstra path in yellow.

(For this assignment, there are no unit tests.  You should consider your code to meet the requirements if the app successfully generates aan appropriate shortest path on the output image.)

Once you have it working, feel free to experiment with different points on the cspace map, or even generate your own map and play with it!  (Purely optional, but fun!)

If you want to generate a map, here's the process I used to create it.
  1) Create a 300x300 .bmp in Microsoft Paint, using black to represent obstacles and white to represent open space.  
  2) Run the included python script imagegen.py to generate a cspace csv from the .bmp.  You can adjust the robot size by editing line 50 (Try changing the 6 to a 3 or 10 or some other number.)
  3) If you want to generate an image of the cspace by itself, with no path, I modified the PathFinder app and commented out lines 68-71 (the section calling Dijkstra method and adding the yellow line to the image) (see cspace_image.jpg for the included map)
