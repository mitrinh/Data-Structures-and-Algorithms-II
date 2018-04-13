//
//      Name:        Trinh, Michael
//      Homework:    2
//      Due:         11/29/17
//      Course:      cs-241-02-f17
//
//      Description:
//                   Finds the shortest path in a maze, prints it with the path, 
//                      and its path length.
//

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Stack;

public class Maze {

    int pathLength;
    int originVertexColumn;
    int originVertexRow;
    
    public Maze() {
        this.pathLength = 0;
        this.originVertexColumn = 0;
        this.originVertexRow = 0;
    }

    public void getShortestPath(char[][] maze, int rows, int columns) {
        boolean[][] adjacencyMatrix = new boolean[rows][columns]; 
        getCoordinates(maze, rows, columns); // gets coordinates of originVertex
        boolean done = false;
        Vertex originVertex = new Vertex('S', originVertexRow, originVertexColumn, 0, null);
        mark(originVertex,adjacencyMatrix); // mark origin on adjacency matrix
        Vertex endVertex = new Vertex();
        ArrayDeque<Vertex> queue = new ArrayDeque<>(); // create queue
        queue.add(originVertex);
        while (!done && !queue.isEmpty()) {
            Vertex frontVertex = queue.remove();
            // up
            char up = maze[frontVertex.row + 1][frontVertex.column];
            Vertex neighbor = new Vertex(up, frontVertex.row + 1,
                    frontVertex.column, frontVertex.pathLength + 1, frontVertex);
            if ((up != 'X') && (!done) && (!isVisited(neighbor,adjacencyMatrix))) {
                queue.add(neighbor);
                mark(neighbor,adjacencyMatrix);
                if (neighbor.vertex == 'F') {
                    done = true;
                    endVertex = neighbor;
                } // end if
            } // end if
            // down
            char down = maze[frontVertex.row - 1][frontVertex.column];
            neighbor = new Vertex(down, frontVertex.row - 1,
                    frontVertex.column, frontVertex.pathLength + 1, frontVertex);
            if ((down != 'X') && (!done) && (!isVisited(neighbor,adjacencyMatrix))) {
                queue.add(neighbor);
                mark(neighbor,adjacencyMatrix);
                if (neighbor.vertex == 'F') {
                    done = true;
                    endVertex = neighbor;
                } // end if
            } // end if
            char left = maze[frontVertex.row][frontVertex.column - 1];
            neighbor = new Vertex(left, frontVertex.row,
                    frontVertex.column - 1, frontVertex.pathLength + 1, frontVertex);
            // left
            if ((left != 'X') && (!done) && (!isVisited(neighbor,adjacencyMatrix))) {
                queue.add(neighbor);
                mark(neighbor,adjacencyMatrix);
                if (neighbor.vertex == 'F') {
                    done = true;
                    endVertex = neighbor;
                } // end if
            } // end if
            // right
            char right = maze[frontVertex.row][frontVertex.column + 1];
            neighbor = new Vertex(right, frontVertex.row,
                    frontVertex.column + 1, frontVertex.pathLength + 1, frontVertex);
            if ((right != 'X') && (!done) && (!isVisited(neighbor,adjacencyMatrix))) {
                queue.add(neighbor);
                mark(neighbor,adjacencyMatrix);
                if (neighbor.vertex == 'F') {
                    done = true;
                    endVertex = neighbor;
                } // end if
            } // end if
        } // end while
        if (!done) { // if cannot find path, no solution
            System.out.println("No solution.");
        } else { // if it does then print maze with path and its path length
            pathLength = endVertex.pathLength;
            Stack<Vertex> stack = new Stack<>();
            Vertex vertex = endVertex;
            stack.push(endVertex);
            while (vertex.prev != null) {
                if (maze[vertex.row][vertex.column] != 'S'
                        && maze[vertex.row][vertex.column] != 'F') {
                    maze[vertex.row][vertex.column] = '.';
                } // end if
                vertex = vertex.prev;
                stack.push(vertex);
            } // end while
            System.out.println("\nMaze with path filled in\n"); // prints maze with path
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    System.out.print(maze[i][j]);
                } // end for
                System.out.println("");
            } // end for
            System.out.println("Path = " + pathLength); // prints path length 
        } // end if
    } // end getShortestPath

    // marks point onto adjacencymatrix
    private void mark(Vertex vertex, boolean[][] adjacencyMatrix){
        adjacencyMatrix[vertex.row][vertex.column] = true;
    } // end mark
    
    // checks if the vertex has been visited before
    private boolean isVisited(Vertex neighbor, boolean[][] adjacencyMatrix) {
        if (adjacencyMatrix[neighbor.row][neighbor.column] == true) {
            return true;
        }
        else{
            return false;
        }     
    } // end isVisited

    private static class Vertex {

        char vertex;
        int column;
        int row;
        int pathLength;
        Vertex prev;

        public Vertex() {
            this(' ', 0, 0, 0, null);
        } // end vertex

        public Vertex(char vertex, int row, int column, int pathLength, Vertex prev) {
            this.vertex = vertex;
            this.row = row;
            this.column = column;
            this.pathLength = pathLength;
            this.prev = prev;
        } // end vertex
    } // end vertex

    private void getCoordinates(char[][] maze, int rows, int columns) {
        for (int i = 0; i < rows; i++) { // scan maze for originVertex and endVertex
            for (int j = 0; j < columns; j++) {
                if (maze[i][j] == 'S') { // sets coordinates for originVertex
                    originVertexColumn = j;
                    originVertexRow = i;
                } // end if
            } // end for
        } // end for
    }

    public static void main(String[] args) {

        System.out.println("M. Trinh's Amazing\n");
        System.out.println("Original maze\n");

        if (args.length == 0) { // checks if filename is entered
            System.out.println("usage: java Maze mazeFilename");
            System.exit(1);
        } // end if

        Scanner data = new Scanner(System.in);
        try {
            data = new Scanner(new File(args[0]));
        } catch (FileNotFoundException exception) { // exception if file not found 
            System.out.println("File not found.");
            System.exit(1);
        } // end try-catch

        ArrayList<String> arrayList = new ArrayList<>();
        int columns = 0; // number of columns in the maze
        while (data.hasNext()) {
            String nextLine = data.nextLine();
            if (nextLine.length() > columns) {
                columns = nextLine.length(); // sets the number of columns in the maze
            }
            arrayList.add(nextLine);
        } // end while
        int rows = arrayList.size(); // number of rows in the maze
        
        char[][] array = new char[rows][columns];
        // creates dimensions of the maze
        int row = 0;
        int column = 0;
        for (String element : arrayList) { // element is a line of string
            for (int i = 0; i < element.length(); i++) {
                char character = element.charAt(i); // gets each character from the line of element  
                array[row][column] = character; // set each element to a position
                column += 1; // moves to next element to the right
            } // end for
            row += 1; // loads next row for next line
            column = 0; // resets column to 0 to load for next line
            System.out.println(element); // prints the maze
        } // end for

        Maze maze = new Maze(); // create object of maze class
        maze.getShortestPath(array, rows, columns); // find the shortest path in the maze
    } // end main
} // end maze
