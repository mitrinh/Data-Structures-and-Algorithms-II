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
public interface BinaryNodeInterface<T> {
    public T getData();
    public void setData(T newData);
    public BinaryNodeInterface<T> getLeftChild();
    public BinaryNodeInterface<T> getRightChild();
    public void setLeftChild(BinaryNodeInterface<T> leftChild);
    public void setRightChild(BinaryNodeInterface<T> rightChild);
    public boolean hasLeftChild();
    public boolean hasRightChild();
    public boolean isLeaf();
    public int getNumberOfNodes();
    public int getHeight();
    public BinaryNodeInterface<T> copy();
} // end BinaryNodeInterface
