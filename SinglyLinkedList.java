/**
 *
 * @author tvnguyen7
 */
public class SinglyLinkedList<T> {
    private Node<T> head;
    private int length;
    
    public SinglyLinkedList () {
        head = null;
        length = 0;
    }
    
    public int length () {
        return length;
    }
    
    public void add (T data) {
        head = new Node<>(data, head);
        ++length;
    }
    
    public void print () {
        Node<T> traverse = head;
        
        while (traverse != null) {
            System.out.println(traverse);
            traverse = traverse.getNext();
        }
    }
    
    public void printReverse () {
        print(head);
    }
    
    private void print (Node<T> traverse) {
        if (traverse != null) {
            print (traverse.getNext());
            System.out.println(traverse);
        }
    }
     
    
    public static void main(String[] args) {
        
    }
}
