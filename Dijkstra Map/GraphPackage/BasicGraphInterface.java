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

public interface BasicGraphInterface<T> {
    
    public boolean addVertex(T vertexLabel);
    
    public boolean addEdge(T begin, T end, double edgeWeight);
    
    public boolean addEdge(T begin, T end);
    
    public boolean hasEdge(T begin, T end);
    
    public boolean isEmpty();
    
    public int getNumberOfVertices();
    
    public int getNumberOfEdges();
    
    public void clear();
} // end BasicGraphInterface
