//
//  Name:       Trinh, Michael
//  Project:    2
//  Due:        12/04/17
//  Course:     cs-241-02-f17
//
//  Description:
//              Implement a graph data structure to read a weighted map
//              and finds the shortest path between two cities.
//

import GraphPackage.DirectedGraph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class MyMap {

    public static void main(String[] args) {
        System.out.println("M. Trinh's MyMap \n");
        if (args.length != 2) { // checks if command entered correctly
            System.out.println("usage: java MyMap fromCity$ toCity$");
            System.exit(1);
        } // end if 
        // assume # of arguments = 2
        DirectedGraph<Integer> cityGraph = new DirectedGraph<>();
        ArrayList<String> cityMap = new ArrayList<>();
        cityMap = createMap(cityGraph); // creates the map
        String cityBegin = args[0]; // start
        String cityEnd = args[1]; // end
        if (isinMap(cityMap, cityBegin, cityEnd)) { // checks if cities are found
            Stack<Integer> path = new Stack<>();
            double distance = cityGraph.getCheapestPath(cityMap.indexOf(cityBegin),
                    cityMap.indexOf(cityEnd), path);
            // finds the shortest path between two cities using their code #s
            if (distance == 0) {
                System.out.println("It is unreachable to go from " + cityBegin
                        + " to " + cityEnd);
            } // end if
            if (distance > 0) {
                /* path stack contains a city and its predecessors */
                while (!path.isEmpty()) {
                    int code = path.pop();
                    System.out.println(cityMap.get(code));
                } // end while
                System.out.println("Distance = " + distance + '\n');
            } // end if
        } // end if
    } // end main

    private static ArrayList<String> createMap(DirectedGraph<Integer> cityGraph) {
        Scanner data = new Scanner(System.in);
        try {
            data = new Scanner(new File("map.txt")); // get map data
        } catch (FileNotFoundException exception) { // exception if file not found 
            System.out.println("File not found.");
            System.exit(1);
        } // end try-catch

        ArrayList<String> cityMap = new ArrayList<>();
        while (data.hasNextLine()) {
            String[] line = data.nextLine().split(" ");
            // seperates the line into Strings by whitespace and puts in an array
            if (line[0].equals("city")) { // checks if city
                int code = Integer.parseInt(line[1]); // convert String to Integer
                String label = line[2];
                if (code < 0) {
                    System.out.println("City code numbers cannot be negative.");
                } else {
                    /* allocates enough space to add city, filling empty space */
                    while (code >= cityMap.size()) {
                        cityMap.add(null);
                    } // end while
                    cityMap.set(code, label);
                    // adds the city to the corresponding index of its code
                    cityGraph.addVertex(code); // adds city to graph 
                } // end if
            } // end if
            else if (line[0].equals("road")) { // checks if road
                int cityBegin = Integer.parseInt(line[1]); // start
                int cityEnd = Integer.parseInt(line[2]); // end
                double distance = Double.parseDouble(line[3]); // cost between
                if (distance <= 0) {
                    System.out.println("A distance between two cities cannot"
                            + " be negative or zero.");
                } else {
                    cityGraph.addEdge(cityBegin, cityEnd, distance);
                    // adds road to graph with its distance
                } // end if
            } // end else if 
        } // end while
        data.close();
        return cityMap;
    } // end createMap

    private static boolean isinMap(ArrayList<String> cityMap, String cityBegin,
            String cityEnd) {
        boolean found = true;
        /* checks if the both cities are in the map */
        if (!(cityMap.contains(cityBegin))) {
            System.out.println(cityBegin + " not found.");
            found = false;
        } // end if
        if (!(cityMap.contains(cityEnd))) {
            System.out.println(cityEnd + " not found.");
            found = false;
        } // end if
        return found;
    } // end isinMap
} // end MyMap
