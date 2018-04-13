//
//  Name:       Trinh, Michael
//  Project:    1
//  Due:        10/20/2017
//  Course:     cs-241-02-f17
//
//  Description:
//                  Create an postfix expression tree that contain operands and 
//                  arithmetic operators, evaluate that expression, then 
//                  output the expression tree in postfix notation.
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
