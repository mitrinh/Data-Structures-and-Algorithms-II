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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

class Vertex<T> implements VertexInterface<T>{  
    private T label;
    private ArrayList<Edge> edgeList;
    private boolean visited; // true if visited
    private VertexInterface<T> previousVertex; // on path to this vertex
    private double cost;

    public Vertex(T vertexLabel) {
        label = vertexLabel;
        edgeList = new ArrayList<>();
        visited = false;
        previousVertex = null;
        cost = 0;
    } // end constructor

    public T getLabel(){
        return label;
    } // end getLabel

    public void visit(){
        visited = true;
    } // end visit

    public void unvisit(){
        visited = false;
    } // end unvisit

    public boolean isVisited(){
        return visited;
    } // end isVisited
    
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight) {
        boolean result = false;
        if (!this.equals(endVertex)) { // vertices are distinct
            Iterator<VertexInterface<T>> neighbors = this.getNeighborIterator();
            boolean duplicateEdge = false;
            while (!duplicateEdge && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor)) {
                    duplicateEdge = true;
                }
            } // end while
            if (!duplicateEdge) {
                Edge edge = new Edge(endVertex,edgeWeight);
                edgeList.add(edge);
                result = true;
            } // end if
        } // end if
        return result;
    } // end connect

    public boolean connect(VertexInterface<T> endVertex) {
        return connect(endVertex, 0);
    } // end connect

    public Iterator<VertexInterface<T>> getNeighborIterator() {
        return new neighborIterator();
    } // end getNeighborIterator
    
    public Iterator<Double> getWeightIterator(){
        return new weightIterator();
    }// end getWeightIterator

    public boolean hasNeighbor() {
        return !edgeList.isEmpty();
    } // end hasNeighbor

    public VertexInterface<T> getUnvisitedNeighbor() {
        VertexInterface<T> result = null;
        Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
        while ((neighbors.hasNext() && (result == null)))
        {
            VertexInterface<T> nextNeighbor = neighbors.next();
            if (!nextNeighbor.isVisited()) {
                result = nextNeighbor;
            } // end if
        } // end while
        return result;
    } // end getUnvisitedNeighbor
    
    public void setPredecessor(VertexInterface<T> predecessor) {
        previousVertex = predecessor;
    } // end setPredecessor

    public VertexInterface<T> getPredecessor() {
        return previousVertex;
    } // end getPredecessor

    public boolean hasPredecessor() {
        return (previousVertex != null);
    } // end hasPredecessor

     public void setCost(double newCost) {
         cost = newCost;
     } // end setCost
    
    public double getCost() {
        return cost;
    } // end getCost
    
    public boolean equals(Object other) {
        boolean result;
        if ((other == null) || (getClass() != other.getClass())) {
            result = false;
        } 
        else {
            Vertex<T> otherVertex = (Vertex<T>) other;
            result = label.equals(otherVertex.label);
        } // end if
        return result;
    } // end equals

    private class neighborIterator implements Iterator<VertexInterface<T>> {

        private Iterator<Edge> edges;

        private neighborIterator() {
            edges = edgeList.iterator();
        } // end default constructor

        public boolean hasNext() {
            return edges.hasNext();
        } // end hasNext

        public VertexInterface<T> next() {
            VertexInterface<T> nextNeighbor = null;
            if (edges.hasNext()) {
                Edge edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getEndVertex();
            } else {
                throw new NoSuchElementException();
            } // end if
            return nextNeighbor;
        } // end next

        public void remove() {
            throw new UnsupportedOperationException();
        } // end remove
    } // end neighborIterator

    private class weightIterator implements Iterator<Double> {
        private Iterator<Edge> edges;
        
        private weightIterator() {
            edges = edgeList.iterator();
        } // end default constructor

        public boolean hasNext() {
            return edges.hasNext();
        } // end hasNext

        public Double next() {
            double weight = 0;
            if (edges.hasNext()) {
                Edge edgeToNextNeighbor = edges.next();
                weight = edgeToNextNeighbor.getWeight();
            } 
            else {
                throw new NoSuchElementException();
            } // end if
            return weight;
        } // end next
        
        public void remove() {
            throw new UnsupportedOperationException();
        } // end remove        
    } // end weightIterator
    
    protected class Edge {

        private VertexInterface<T> vertex; // end vertex
        private double weight;

        protected Edge(VertexInterface<T> endVertex, double edgeWeight) {
            vertex = endVertex;
            weight = edgeWeight;
        } // end constructor

        protected VertexInterface<T> getEndVertex() {
            return vertex;
        } // end getEndVertex

        protected double getWeight() {
            return weight;
        } // end getWeight
    } // end Edge
} // end Vertex
