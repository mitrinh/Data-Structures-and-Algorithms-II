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

public interface DictionaryInterface<K,V> {
    public V add(K key, V value);
    public V remove(K key);
    public V getValue(K key);
    public boolean contains(K key);
    public Iterator<K> getKeyIterator();
    public Iterator<V> getValueIterator();
    public boolean isEmpty();
    public int getSize();
    public void clear();
} // end dictionaryInterface
