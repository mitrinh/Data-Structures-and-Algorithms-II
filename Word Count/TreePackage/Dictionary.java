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
public class Dictionary<K extends Comparable<? super K>,V> 
       implements DictionaryInterface<K,V> {
    
    private SearchTreeInterface<Entry<K,V>> bst;
    
    public Dictionary() {
        bst = new BinarySearchTree<>();
    } // end default constructor
    
    // add an entry to the dictionary, returns the value of that key
    public V add(K key,V value) {
        Entry<K, V> newEntry = new Entry<>(key, value);
        Entry<K, V> returnedEntry = bst.add(newEntry);
        V result = null;
        if (returnedEntry != null) {
            result = returnedEntry.getValue();
        }
        return result;
    } // end add
    
    // removes an entry from the dictionary, returns the value of that key
    public V remove(K key) {
        Entry<K,V> findEntry = new Entry<>(key, null);
        Entry<K,V> returnedEntry = bst.remove(findEntry);
        V result = null;
        if (returnedEntry != null) {
            result = returnedEntry.getValue();
        }
        return result;
    } // end remove
    
    // gets the value of that key in dictionary and returns it
    public V getValue(K key) {
        Entry<K,V> findEntry = new Entry<>(key, null);
        Entry<K,V> returnedEntry = bst.getEntry(findEntry);
        V result = null;
        if (returnedEntry != null) {
            result = returnedEntry.getValue();
        }
        return result;
    } // end getValue
    
    // checks if the dictionary contains K key
    public boolean contains(K key) {
        Entry<K, V> findEntry = new Entry<>(key, null);
        return bst.contains(findEntry);
    } // end contains    
    
    // Iterator for the key
    public Iterator<K> getKeyIterator() {
        return new KeyIterator();
    } // end getKeyIterator
    
    private class KeyIterator implements Iterator<K> {
        Iterator<Entry<K,V>> localIterator;
        
        public KeyIterator() {
            localIterator = bst.getInorderIterator();
        } // end default constructor
        
        public boolean hasNext() {
            return localIterator.hasNext();
        } // end hasNext
        
        public K next() {
            Entry<K,V> nextEntry = localIterator.next();
            return nextEntry.getKey();
        } // end next
        
        public void remove() {
            throw new UnsupportedOperationException();
        } // end remove 
    } // end getKeyIterator
    
    
    // iterator for value
    public Iterator<V> getValueIterator() {
        return new ValueIterator();
    } // end getKeyIterator
    
    private class ValueIterator implements Iterator<V> {
        Iterator<Entry<K,V>> localIterator;
        
        public ValueIterator() {
            localIterator = bst.getInorderIterator();
        } // end default constructor
        
        public boolean hasNext() {
            return localIterator.hasNext();
        } // end hasNext
        
        public V next() {
            Entry<K, V> nextEntry = localIterator.next();
            return nextEntry.getValue();
        } // end next
        
        public void remove() {
            throw new UnsupportedOperationException();
        } // end remove 
    } // end getValueIterator
    
    // returns true if the BST is empty
    public boolean isEmpty() {
        return bst.isEmpty();
    } // end isEmpty

    // returns size of the BST
    public int getSize() {
        return bst.getNumberOfNodes();
    } // end getSize
    
    // clears the BST
    public void clear() {
        bst.clear();
    } // end clear
    
    // Class Entry
    private class Entry<S extends Comparable<? super S>,T>
            implements Comparable<Entry<S,T>> {
        private S key;
        private T value;
        
        private Entry(S searchkey, T datavalue) {
            key = searchkey;
            value = datavalue;
        } // end constructor
        
        public int compareTo(Entry<S,T> other) {
            return key.compareTo(other.key);
        } // end compareto
        
        public boolean equals(Entry T) {
            return key.equals(T.key);
        } // end equals
        
        public S getKey() {
            return key;
        } // end getKey
        
        public T getValue() {
            return value;
        } // end getValue
        
        public void setValue(T newValue) {
            value = newValue;
        } // end setValue
    } // end entry
} // end dictionary
