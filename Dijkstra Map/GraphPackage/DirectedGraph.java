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

import java.util.Comparator;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


public class DirectedGraph<T> implements GraphInterface<T> {

    private HashMap<T, VertexInterface<T>> vertices;
    private int edgeCount;

    public DirectedGraph() {
        vertices = new HashMap<>();
        edgeCount = 0;
    } // end default constructor

    public boolean addVertex(T vertexLabel) {
        VertexInterface<T> isDuplicate
                = vertices.put(vertexLabel, new Vertex(vertexLabel));
        return (isDuplicate == null); // was add to dictionary successful?
    } // end addVertex

    public boolean addEdge(T begin, T end, double edgeWeight) {
        boolean result = false;
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);
        if ((beginVertex != null) && (endVertex != null)) {
            result = beginVertex.connect(endVertex, edgeWeight);
        } // end if
        if (result) {
            edgeCount++;
        } // end if
        return result;
    } // end addEdge

    public boolean addEdge(T begin, T end) {
        return addEdge(begin, end, 0);
    } // end addEdge

    public boolean hasEdge(T begin, T end) {
        boolean found = false;
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);
        if ((beginVertex != null) && (endVertex != null)) {
            Iterator<VertexInterface<T>> neighbors
                    = beginVertex.getNeighborIterator();
            while (!found && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor)) {
                    found = true;
                } // end if
            } // end while
        } // end if
        return found;
    } // end hasEdge

    public boolean isEmpty() {
        return vertices.isEmpty();
    } // end isEmpty

    public void clear() {
        vertices.clear();
        edgeCount = 0;
    } // end clear

    public int getNumberOfVertices() {
        return vertices.size();
    } // end getNumberOfVertices

    public int getNumberOfEdges() {
        return edgeCount;
    } // end getNumberOfEdges

    protected void resetVertices() {
        Iterator<VertexInterface<T>> vertexIterator = vertices.values().iterator();
        while (vertexIterator.hasNext()) {
            VertexInterface<T> nextVertex = vertexIterator.next();
            nextVertex.unvisit();
            nextVertex.setCost(0);
            nextVertex.setPredecessor(null);
        } // end while
    } // end resetvertices

    public Queue<T> getBreadthFirstTraversal(T origin) {
        throw new UnsupportedOperationException("Breadth First Traversal not supported");
    } // end getBreadthFirstTraversal

    public Queue<T> getDepthFirstTraversal(T origin) {
        resetVertices();
        Queue<T> traversalOrder = new LinkedList<>();
        Stack<VertexInterface<T>> vertexStack = new Stack<>();
        VertexInterface<T> originVertex = vertices.get(origin);
        originVertex.visit();
        traversalOrder.add(origin);
        vertexStack.push(originVertex);
        while (!vertexStack.isEmpty()) {
            VertexInterface<T> topVertex = vertexStack.peek();
            if (topVertex.getUnvisitedNeighbor() != null) { // checks for unvisited neighbor
                VertexInterface<T> nextNeighbor = topVertex.getUnvisitedNeighbor();
                nextNeighbor.visit();
                traversalOrder.add(nextNeighbor.getLabel());
                vertexStack.push(nextNeighbor);
            }
            else { // all neighbors visited
                vertexStack.pop();
            } // end if
        } // end while
        return traversalOrder;
    } // end getDepthFirstTraversal
   
    public Stack<T> getTopologicalOrder() {
        throw new UnsupportedOperationException("Topological Order not supported");
    } // end getTopologicalOrder
    
    public int getShortestPath(T begin, T end, Stack<T> path) {
        throw new UnsupportedOperationException("getShortestPath not supported");
    } // end getShortestPath
        
    public double getCheapestPath(T begin, T end, Stack<T> path) {
        resetVertices();
        boolean done = false;
        
        VertexInterface<T> originVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);
        PriorityQueue<VertexInterface<T>> priorityQueue = new PriorityQueue<>(
                new Comparator<VertexInterface<T>>() {
                    /* makes the objects comparable for priority queue */
                    public int compare(VertexInterface<T> vertex1, 
                                       VertexInterface<T> vertex2) {
                        Double answer = vertex1.getCost() - vertex2.getCost();
                        // priority queue sorts in ascending order of cost
                        int result = answer.intValue();
                        return result;
                } // end compare
            }); // end priorityQueue
        priorityQueue.add(originVertex);
        
        while (!done && !priorityQueue.isEmpty()) {
            VertexInterface<T> frontVertex = priorityQueue.remove();
            if (!frontVertex.isVisited()) { // checks if vertex is unvisited
                frontVertex.visit();
                if (frontVertex.equals(endVertex)) { // checks if they're equal
                    done = true;
                    endVertex = frontVertex;
                } // end if            
                else {
                    Iterator <VertexInterface<T>> neighbors  =
                                                  frontVertex.getNeighborIterator();
                    Iterator <Double> weights = frontVertex.getWeightIterator();
                    while (neighbors.hasNext()) {
                        /* neighbors and weights have to iterate at same time */
                        VertexInterface<T> neighbor = neighbors.next();
                        double weight = weights.next();
                        if (!neighbor.isVisited()) {
                            double nextCost = weight + 
                                              frontVertex.getCost();
                            neighbor.setCost(nextCost);
                            neighbor.setPredecessor(frontVertex);
                            priorityQueue.add(neighbor);
                        } // end if
                    } // end while  
                } // end if
            } // end if
        } // end while
        double pathCost = endVertex.getCost();
        path.push(endVertex.getLabel());
        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor()) {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        } // end while
        return pathCost;
    } // end getCheapestPath
} // end DirectedGraph