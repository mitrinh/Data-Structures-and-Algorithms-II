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
public interface BinaryTreeInterface<T> 
       extends TreeInterface<T>, TreeIteratorInterface<T> { 
    public void setTree(T rootData);
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
                                    BinaryTreeInterface<T> rightTree);
} // end BinaryTreeInterface
