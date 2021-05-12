import java.util.Set;

public class BSTMap<Key extends Comparable<Key>, Value> implements Map61B<Key, Value>{
    private class Node<Key, Value>{
        public Key key;
        public Value value;
        public Node<Key, Value> leftSmaller;
        public Node<Key, Value> rightBigger;

        public Node(Key key, Value value){
            this.key = key;
            this.value = value;
        }
    }

    public int size;

    /** Removes all of the mappings from this map. */
    public void clear(){

    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(Key key){

    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public Value get(Key key){

    }

    /* Returns the number of key-value mappings in this map. */
    public int size(){

    }

    /* Associates the specified value with the specified key in this map. */
    public void put(Key key, Value value){

    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<Key> keySet(){

    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public Value remove(Key key){

    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public Value remove(Key key, Value value){

    }
}