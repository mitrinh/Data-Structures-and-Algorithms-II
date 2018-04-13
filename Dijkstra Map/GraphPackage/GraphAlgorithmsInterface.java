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

package GraphPackage;
import java.util.Stack;
import java.util.Queue;

public interface GraphAlgorithmsInterface<T> {
    
    public Queue<T> getBreadthFirstTraversal(T origin);
    
    public Queue<T> getDepthFirstTraversal(T origin);
    
    public Stack<T> getTopologicalOrder();
    
    public int getShortestPath(T begin, T end, Stack<T> path);
    
    public double getCheapestPath(T begin, T end, Stack<T> path);
} // end GraphAlgorithmsInterface
