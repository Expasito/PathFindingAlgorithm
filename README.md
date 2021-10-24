# PathFindingAlgorithm
Java based 2D array pathfinding algorithm

To use the algorthim, create a 2d int array with any constant width and length. Set all values to 0.

Then create an instance of Pathing.

Use the instance to run the load method and pass in the start x, start y, end x, end y, and the array

Then use the instance to run the getPath method. The return is a 2d array of of x and y cordinates on the array.

To display all of the cordinates, run the print method

To visually see the path, run the display method.

An example is seen in BaseFile.java


This is a version 2 of the algorithm where now, weights can be added that add to the cost. Walls are represented with a '-1'. A zero extra cost tile uses a 0 and any number above 0 adds that much extra to the cost.
