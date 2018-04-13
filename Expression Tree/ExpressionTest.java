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

import TreePackage.ExpressionTree;
public class ExpressionTest {
    public static void main(String[] args) {
        // Expression is a well formed postfix expression
        ExpressionTree expr = new 
                    // ExpressionTree(new String[]{"a", "b", "2", "/", "10", "*", "+"});
                    // ExpressionTree(new String[]{"a","b","+"});
                       ExpressionTree(new String[]{"a", "b", "2", "/", "+"});
                    // infix: a + b / 2
        expr.setVariable("a", 1.5);
        expr.setVariable("b", 2);
        System.out.println(expr.evaluate());
        expr.displayPostfix();
    }
}
