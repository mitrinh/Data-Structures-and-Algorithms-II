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
public interface ExpressionTreeInterface 
                 extends BinaryTreeInterface<String> {
    /** Computes the value of the expression in this tree.
        @return the value of the expression */
    public double evaluate();
    public void setVariable(String name, double value);
    public void displayPostfix();
} // end ExpressionTreeInterface{

