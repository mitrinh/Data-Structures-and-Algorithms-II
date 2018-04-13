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
public class BinarySearchTree<T extends Comparable<? super T>>
       extends BinaryTree<T>
       implements SearchTreeInterface<T>
{
    public BinarySearchTree() {
        super();
    } // end default constructor
    
    public BinarySearchTree(T rootEntry) {
        super();
        setRootNode(new BinaryNode<T>(rootEntry));
    } // end constructor
    
    public void setTree(T rootData) // disable setTree
    {
        throw new UnsupportedOperationException();
    } // end setTree
    
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
                        BinaryTreeInterface<T> rightTree) { // disable setTree
        throw new UnsupportedOperationException();
    } // end setTree
    
    // checks if the binary search tree contains the entry
    public boolean contains(T entry) {
        return getEntry(entry) != null;
    } // end contains
    
    // return the entry in a binary search tree
    public T getEntry(T entry) {
        return findEntry(getRootNode(),entry);
    } // end getEntry
    
    // finds the entry in a binary search tree
    private T findEntry(BinaryNodeInterface<T> rootNode, T entry) {
        T result = null;
        if (rootNode != null) {
            T rootEntry = rootNode.getData();
            if (entry.compareTo(rootEntry) == 0) { // checks if entry is equal to root
                result = rootEntry;
            }
            else if (entry.compareTo(rootEntry) < 0) { // then checks if < root
                result = findEntry(rootNode.getLeftChild(),entry); // recursive
            }
            else { // if not = or < root, it must be >
                result = findEntry(rootNode.getRightChild(),entry); // recursive
            } // end if
        } // end if
        return result;
    } // end findEntry
    
    // adds an entry to the Binary Search Tree, returns the data removed or null
    public T add(T newEntry) {
        T result = null;
        if (isEmpty()) { // makes new entry root if its empty
            setRootNode(new BinaryNode<>(newEntry));
        }
        else {
            result = addEntry(getRootNode(),newEntry);
        }
        return result;
    } // end add
    
    // adds the entry to a nonempty BST
    private T addEntry(BinaryNodeInterface<T> rootNode, T newEntry) {
        assert rootNode != null; // assume rootnode isn't null
        T result = null;
        int comparison = newEntry.compareTo(rootNode.getData());
        if (comparison == 0) { 
        // checks if the new Entry is already in BST
            result = rootNode.getData();
            rootNode.setData(newEntry);
        }
        else if (comparison < 0) { 
        // then checks if new Entry is < root node
            if (rootNode.hasLeftChild()) { // checks if root has leftchild or 
                                           // not & recursively does it again 
                result = addEntry(rootNode.getLeftChild(),newEntry);
            }
            else {
                rootNode.setLeftChild(new BinaryNode<>(newEntry));
                // sets the left child of that root with the node of the data
            } // end if
        }
        else {
            assert comparison > 0; // must be > than root if not = or <
            if (rootNode.hasRightChild()) { // checks if root has rightchild or 
                                            // not & recursively does it again 
                result = addEntry(rootNode.getRightChild(),newEntry);
            }
            else {
                rootNode.setRightChild(new BinaryNode<>(newEntry));
                // sets the right child of that root with the node of the data
            } // end if            
        } // end if
        return result;
    } // end addentry
    
    // remove a node from a BST, returns entry removed
    public T remove(T entry) {
        T oldEntry = null;
        BinaryNodeInterface<T> newRoot = removeEntry(getRootNode(),entry,
                                            oldEntry);
        setRootNode(newRoot);
        return oldEntry;
    } // end remove
    
    private BinaryNodeInterface<T> removeEntry(BinaryNodeInterface<T> rootNode,
                                               T entry,T oldEntry) {
        if (rootNode != null) {
            T rootData = rootNode.getData();
            if (entry.equals(rootNode.getData())) { // entry == root
                oldEntry = rootData;
                removeFromRoot(rootNode);
            }
            else if (entry.compareTo(rootNode.getData()) < 0) { // entry < root
                BinaryNodeInterface<T> leftChild = rootNode.getLeftChild();
                BinaryNodeInterface<T> subtreeRoot = removeEntry(leftChild,entry
                                                                 ,oldEntry);
                rootNode.setLeftChild(subtreeRoot);
            }
            else {
                BinaryNodeInterface<T> rightChild = rootNode.getRightChild();
                rootNode.setRightChild(removeEntry(rightChild,entry,oldEntry));
            } // end if
        } // end if
        return rootNode;
    } // end removeEntry
    
    private BinaryNodeInterface<T> removeFromRoot(BinaryNodeInterface<T> 
                                                  rootNode) {
        if (rootNode.hasLeftChild() && rootNode.hasRightChild()) { // 2 children
            BinaryNodeInterface<T> leftSubtreeRoot = rootNode.getLeftChild();
            BinaryNodeInterface<T> largestNode = findLargest(leftSubtreeRoot);
            rootNode.setData(largestNode.getData());
            rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
        }
        else if (rootNode.hasRightChild()) { // has at most 1 child checks right
            rootNode.getRightChild();
        }
        else { // must be left, if root is a leaf node, it is null
            rootNode.getLeftChild();
        } // end if
        return rootNode;
    } // end removeFromRoot
    
    // finds the largest node in a BST via going to rightmost node
    private BinaryNodeInterface<T> findLargest(BinaryNodeInterface<T> rootNode) 
    {
        if (rootNode.hasRightChild()) {
            rootNode = findLargest(rootNode.getRightChild());
        }
        return rootNode;
    } // end findLargest
    
    // removes the rightmost node, returns the root of the new tree
    private BinaryNodeInterface<T> removeLargest(BinaryNodeInterface<T> 
                                                 rootNode) {
        if (rootNode.hasRightChild()) {
            BinaryNodeInterface<T> rightChild = rootNode.getRightChild();
            BinaryNodeInterface<T> root = removeLargest(rightChild);
            rootNode.setRightChild(root);
        } else {
            rootNode = rootNode.getLeftChild();
        }
        return rootNode;
    } // end removeLargest
    
    public Iterator<T> getInorderIterator() {
        return super.getInorderIterator();
    } // end getInorderIterator

}