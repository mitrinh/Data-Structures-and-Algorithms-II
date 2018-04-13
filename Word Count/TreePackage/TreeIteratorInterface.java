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
public interface TreeIteratorInterface<T> {
    public Iterator<T> getPreorderIterator();
    public Iterator<T> getPostorderIterator();
    public Iterator<T> getInorderIterator();
    public Iterator<T> getLevelOrderIterator(); 
} // end TreeIteratorInterface
