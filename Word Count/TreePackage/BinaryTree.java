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
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTree<T> implements BinaryTreeInterface<T>{
    private BinaryNodeInterface<T> root;
    
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
        if (leftTree != null) {
            root.setLeftChild(leftTree.root);
        }
        if (rightTree != null) {
            root.setRightChild(rightTree.root);
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
    
    protected void setRootNode(BinaryNodeInterface<T> rootNode) {
        root = rootNode;
    } // end setRootNode
    
    protected BinaryNodeInterface<T> getRootNode() {
        return root;
    } // end getRootNode
    
    public void inorderTraverse(){
        inorderTraverse(root);
    } // end inorderTraverse
    
    private void inorderTraverse(BinaryNodeInterface<T> node) {
        if (node != null) {
            inorderTraverse(node.getLeftChild());
            System.out.print(node.getData() + " ");
            inorderTraverse(node.getRightChild());
        } // end inorderTraverse
    }
    
    public void postorderTraverse() {
        postorderTraverse(root);
    } // end postorderTraverse

    private void postorderTraverse(BinaryNodeInterface<T> node) {
        if (node != null) {
            postorderTraverse(node.getLeftChild());
            postorderTraverse(node.getRightChild());
            System.out.print(node.getData() + " ");
        } // end if
    } // end postorderTraverse   
    

    public Iterator<T> getPreorderIterator() {
            throw new UnsupportedOperationException("Preorder not supported");
	} // end getPreorderIterator

    public Iterator<T> getInorderIterator() {
            return new InorderIterator();	
	} // end getInorderIterator
	
    public Iterator<T> getPostorderIterator() {
            throw new UnsupportedOperationException("Postorder not supported");
	} // end getPostorderIterator

    public Iterator<T> getLevelOrderIterator() {
            throw new UnsupportedOperationException("LevelOrder not supported");
	} // end getLevelOrderIterator
        
    private class InorderIterator implements Iterator<T> {
        private Stack<BinaryNodeInterface<T>> nodeStack;
        private BinaryNodeInterface<T> node;

        public InorderIterator() {
            nodeStack = new Stack<>();
            node = root;
        } // end default constructor

        public boolean hasNext() {
            return !nodeStack.isEmpty() || (node != null);
        } // end hasNext

        public T next() {
            BinaryNodeInterface<T> nextnode = null;
            while (node != null) {
                nodeStack.push(node);
                node = node.getLeftChild();
            } // end while
            if (!nodeStack.isEmpty()) {
                nextnode = nodeStack.pop();
                assert nextnode != null;
                node = nextnode.getRightChild();
            } else {
                throw new NoSuchElementException();
            }
            return nextnode.getData();
        }// end next

        public void remove() {
            throw new UnsupportedOperationException();
        }// end remove
    }// end InorderIterator
}// end BinaryTree
