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
import java.util.Iterator;
import java.util.Stack;

public class BinaryTree<T> implements BinaryTreeInterface<T>{
    private BinaryNode<T> root;
    private final Stack<BinaryNode<T>> Stack = new Stack<>();
    
    public BinaryTree(){
        root = null;
    } // end default constructor
    public BinaryTree(T rootData) {
        root = new BinaryNode<>(rootData);
    } // end constructor
    
    public BinaryTree(T rootData, BinaryTree<T> leftTree, 
                                  BinaryTree<T> rightTree) {
        privateSetTree(rootData, leftTree, rightTree);
    } // end constructor
    
    public void setTree(T rootData) {
        root = new BinaryNode<>(rootData);
    } // end setTree
    
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
                                    BinaryTreeInterface<T> rightTree) {
        privateSetTree(rootData, (BinaryTree<T>)leftTree,
                                 (BinaryTree<T>)rightTree);
    } // end setTree
    
    private void privateSetTree(T rootData, BinaryTree<T> leftTree, 
                                            BinaryTree<T> rightTree) {
        root = new BinaryNode<>(rootData);
        if ((leftTree != null) && !leftTree.isEmpty()) {
            root.setLeftChild(leftTree.root);
        } 
        if ((rightTree != null) && !rightTree.isEmpty()) {
            if (rightTree != leftTree) {
                root.setRightChild(rightTree.root);
            } else {
                root.setRightChild(rightTree.root.copy());
            }
        } // end if
        if ((leftTree != null) && (leftTree != this)) {
            leftTree.clear();
        }
        if ((rightTree != null) && (rightTree != this)) {
            rightTree.clear();
        }   
    } // end privateSetTree
       
    public T getRootData() {
        T rootData = null;
        if (root!=null) {
            rootData = root.getData();
        }
        return rootData;
    }
    
    public int getHeight() {
        return root.getHeight();
    } // end getHeight

    public int getNumberOfNodes() {
        return root.getNumberOfNodes();
    } // end getNumberOfNodes

    public boolean isEmpty() {
        return root == null;
    } // end isEmpty
    
        public void clear() {
        root = null;
    } // end clear

    protected void SetRootData(T rootData) {
        root.setData(rootData);
    } // end setRootNode
    
    protected void setRootNode(BinaryNode<T> rootNode) {
        root = rootNode;
    } // end setRootNode
    
    protected BinaryNode<T> getRootNode() {
        return root;
    } // end getRootNode
    
    public void inorderTraverse(){
        inorderTraverse(root);
    } // end inorderTraverse
    
    private void inorderTraverse(BinaryNode<T> node) {
        if (node != null) {
            inorderTraverse(node.getLeftChild());
            System.out.print(node.getData() + " ");
            inorderTraverse(node.getRightChild());
        } // end inorderTraverse
    }
    
    public void postorderTraverse() {
        postorderTraverse(root);
    } // end postorderTraverse

    private void postorderTraverse(BinaryNode<T> node) {
        if (node != null) {
            postorderTraverse(node.getLeftChild());
            postorderTraverse(node.getRightChild());
            System.out.print(node.getData() + " ");
        } // end if
    } // end postorderTraverse   
    
    public Iterator<T> getPreorderIterator() { // Data,Left,Right
        return null;
    }
  
    public Iterator<T> getInorderIterator() { // Left,Data,Right
        return null;
    }
    
    public Iterator<T> getPostorderIterator() { // Left,Right,Data
        return null;
    }
    
    public Iterator<T> getLevelOrderIterator() { // height 1 -> 2 -> 3 -> ...
        return null;
    }

}
