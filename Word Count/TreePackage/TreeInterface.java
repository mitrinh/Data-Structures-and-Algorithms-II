//
//  Name:       Trinh, Michael
//  Homework:   1
//  Due:        October 25,2017
//  Course:     cs-241-02-f17
//
//  Description:    
//              Implement the ADT dictionary using a BST and use it as a 
//                  word count program.
//


package TreePackage;
public interface TreeInterface<T> {
    public T getRootData();
    public int getHeight();
    public int getNumberOfNodes();
    public boolean isEmpty();
    public void clear();
} // end TreeInterface

