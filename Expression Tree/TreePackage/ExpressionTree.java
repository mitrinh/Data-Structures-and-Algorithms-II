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
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.ArrayList;
public class ExpressionTree extends BinaryTree<String> 
                            implements ExpressionTreeInterface {
    
    private final ArrayList<String> names = new ArrayList<>();
    private final ArrayList<Double> values = new ArrayList<>();
    
    public ExpressionTree() {
    } // end default constructor
    
    // creates the Expression tree from a string array of inputs
    public ExpressionTree(String[] input) {
        System.out.println("M. Trinh's Expression Tree" + "\n");
        Stack <BinaryNode<String>> TreeStack = new Stack(); 
        for (int i = 0; i < input.length; i++) {
            // checks if input is a variable or a double
            if ((isVariable(input[i])) || (isDouble(input[i])))  { 
                BinaryNode<String> node = new 
                                   BinaryNode<>(input[i]);
                TreeStack.push(node); // push the variable or double
            }       
            else if (isOperator(input[i])) { // checks if input is an operator
                BinaryNode<String> root = new 
                                   BinaryNode<>(input[i]);
                root.setRightChild(TreeStack.pop()); // pop for the right child 
                root.setLeftChild(TreeStack.pop()); // pop for the left child
                setRootNode(root); // operator should always be root node                    
                TreeStack.push(root); // then push the tree 
            }                  
            else { // throws an exception if neither operand nor operator
                throw new NoSuchElementException(input[i] + " is undefined");
            } // end if     
        } // end for loop
    } // end constructor

    // checks if the input is an arithmetic operator
    private boolean isOperator(String input) {
        if (input.length() != 1){ 
            return false; // operators only have 1 char
        }
        char temp[] = input.toCharArray(); // convert string to char array
        return (temp[0] == '+') || (temp[0] == '-') || (temp[0] == '*') ||
               (temp[0] == '/'); // arithmetic operators only
    } // end isOperator    
    
    // checks if the input is a variable
    private boolean isVariable(String input) {
        char temp[] = input.toCharArray();
        for (char character : temp) {
            if(!(Character.isLetter(character))) {
                return false;
            }
        } // end for loop
        return true;
    } // end isVariable
    
    // checks if the input is a double
    private boolean isDouble(String input) {
        char temp[] = input.toCharArray();
        for (char character : temp) {
            if (!(Character.isDigit(character))) {
                return false;
            }
        } // end for loop
        return true;
    } // end isDouble
    
    // evaluate the input expression
    public double evaluate() {
        System.out.println("result of postfix expression: ");
        return evaluate(getRootNode());
    } // end evaluate

    // evaluate the input expression
    private double evaluate(BinaryNodeInterface<String> rootNode) {
        double result;
        if (rootNode == null) {
            result = 0;
        } else if (rootNode.isLeaf()) {
            String variable = rootNode.getData();
            result = getValueOf(variable);
        } else {
            double firstOperand = evaluate(rootNode.getLeftChild());
            double secondOperand = evaluate(rootNode.getRightChild());
            String operator = rootNode.getData();
            result = compute(operator, firstOperand, secondOperand);
        } // end if
        return result;
    } // end evaluate

    // get the value of a variable
    private double getValueOf(String variable) {
        if(isVariable(variable)) { // checks if name is a variable
            if (names.contains(variable)) { // checks if arraylist has variable
                return values.get(names.indexOf(variable)); 
                // returns the data in the arraylist of values at the position
                // names is in
            }
            else { // throws an exception if a variable doesn't have a value
                throw new NoSuchElementException(variable + " doesn't have a "
                                                 + "value set to it");
            }
        }
        else if (isDouble(variable)) { 
        // converts the string to a double if it's a double
            double number = Double.parseDouble(variable);
            return number;
        }
        else { // throws an exception if variable is undefined
            throw new NoSuchElementException(variable + " isn't a variable.");
            }
    } // end getValueOf

    // does a simple operation between two operands
    private double compute(String operator, double firstOperand,
                                            double secondOperand) {
        double result;
        switch (operator) { // check which operator to compute with
            case "+" :
                result = firstOperand + secondOperand;
                break;
            case "-" :
                result = firstOperand - secondOperand;
                break;
            case "*" :
                result = firstOperand * secondOperand;
                break;
            case "/" :
                if (secondOperand == 0) { 
                    throw new ArithmeticException("divide by zero error");
                }
                result = firstOperand / secondOperand;
                break;
            default :
                throw new NoSuchElementException("operator is undefined");
        }
        return result;
    } // end compute
    
    // set the variable name with a value
    public void setVariable(String name, double value){
        if(isVariable(name)) { // checks if name is a variable
            if (names.contains(name)) { // if name already has value
                values.set(names.indexOf(name), value); // replace value
            } // end if
            else { // else add to the respective ArrayList
                names.add(name);
                values.add(value);
            } // end else        
        }
        else {
            // throw exception if the string input isn't a variable
            throw new NoSuchElementException(name + " isn't a variable.");
        }
    } // end setVariable
    
    /** outputs the ET in postfix format in one line, 
    each token is separated by one space **/
    public void displayPostfix(){
        /* if input is "a","b","2","/","+", then result should be a b 2 / + */
        System.out.println("Expression Tree in Postfix format: ");
        postorderTraverse(); /* postorder traversal displays 
                                ET in postfix notation */
        System.out.println("");
    } // end displayPostfix
    
} // end ExpressionTree
