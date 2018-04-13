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
public interface BinaryTreeInterface<T> 
       extends TreeInterface<T>, TreeIteratorInterface<T> { 
    public void setTree(T rootData);
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
                                    BinaryTreeInterface<T> rightTree);
} // end BinaryTreeInterface
