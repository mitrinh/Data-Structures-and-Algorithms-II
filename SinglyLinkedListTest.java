/**
 *
 * @author tvnguyen7
 */
public class SinglyLinkedListTest {
    
    public static void main(String[] args) {
        SinglyLinkedList<String> sll = new SinglyLinkedList<>();
        
        sll.add("hello");
        sll.add("world");
        
        System.out.println("length = " + sll.length());
        
        System.out.println("Forward:");
        sll.print();
        
        System.out.println("Reverse:");
        sll.printReverse();
    }
    
}
