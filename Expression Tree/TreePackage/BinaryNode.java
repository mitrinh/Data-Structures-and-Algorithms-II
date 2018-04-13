//
//  Name:       Trinh, Michael
//  Project:    1
//  Due:        10/20/2017
//  Course:     cs-241-02-f17
//
//  Description:
//              Create an postfix expression tree that contain operands and arithmetic
//                  operators, evaluate that expression, then output the 
//                  expression tree in postfix notation.
//

package TreePackage;
public class BinaryNode<T> implements BinaryNodeInterface<T> {

    private T data;
    private BinaryNode<T> left;
    private BinaryNode<T> right;

    public BinaryNode() {
        this(null);
    } // end default constructor

    public BinaryNode(T dataPortion) {
        this(dataPortion, null, null);
    } // end constructor

    public BinaryNode(T dataPortion, BinaryNode<T> newLeftChild,
                                     BinaryNode<T> newRightChild) {
        data = dataPortion;
        left = newLeftChild;
        right = newRightChild;
    } // end constructor

    public T getData() {
        return data;
    } // end getData

    public BinaryNode<T> getLeftChild() {
        return left;
    } // end getLeftChild

    public BinaryNode<T> getRightChild() {
        return right;
    } // end getRightChild
    
    public void setData(T newData) {
        data = newData;
    } // end setData
    
    public void setLeftChild(BinaryNodeInterface<T> leftChild) {
        left = (BinaryNode<T>)leftChild;
    } // end setLeftChild
    
    public void setRightChild(BinaryNodeInterface<T> rightChild) {
        right = (BinaryNode<T>)rightChild;
    } // end setRightChild
    
    public boolean hasLeftChild() {
        return left != null;
    } // end hasLeftChild
    
    public boolean hasRightChild() {
        return right != null;
    } // end hasRightChild
    
    public boolean isLeaf() {
        return (left == null) && (right == null);
    } // end isLeaf()
    
    // returns the number of nodes in a tree 
    
        public int getNumberOfNodes() {
        int leftNumber = 0;
        int rightNumber = 0;
        if (left != null) {
            leftNumber = left.getNumberOfNodes();
        }
        if (right != null) {
            rightNumber = right.getNumberOfNodes();
        }
        return (1 + leftNumber + rightNumber); // total nodes = node itself + 
                                               // left subtree + right subtree
    } // end getNumberOfNodes
    
    public int getHeight() {
        return getHeight(this);
    } // end getHeight
    
    private int getHeight(BinaryNode<T> node) {
        int height = 0;
        if (node != null) {
            height = 1 + Math.max(getHeight(node.left),
                                  getHeight(node.right));
        }
        return height;   
    } // end getHeight
    
    public BinaryNodeInterface<T> copy() {
        BinaryNode<T> newRoot = new BinaryNode<>(data);
        if (left != null) {
            newRoot.left = (BinaryNode<T>) left.copy();
        }
        if (right != null) {
            newRoot.right = (BinaryNode<T>) right.copy();
        }
        return newRoot;
    } // end copy
}
