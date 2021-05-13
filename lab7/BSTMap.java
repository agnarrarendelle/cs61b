import java.util.HashSet;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private class Node{
        public K key;
        public V value;
        public Node leftSmaller;
        public Node rightBigger;

        public Node(K key, V value){
            this.value = value;
            this.key = key;
        }
    }

    private Node rootNode;
    public int size;

    public BSTMap(){
        this.rootNode = null;
    }
    @Override
    public void clear(){
        this.rootNode = null;
        this.size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        return get(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        return getHelper(this.rootNode, key);
    }

    private V getHelper(Node node, K key){
        if(node == null){
            return null;
        }
        //return -1 if node.key is less than key
        //return 1 if node.key is bigger than key
        //return 0 if node.key is the same as key
        int cmp = key.compareTo(node.key);

        if(cmp == 0){
            return node.value;
        }else if(cmp < 0){
            return getHelper(node.leftSmaller, key);
        }else {
            return getHelper(node.rightBigger, key);
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size(){
        return this.size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        rootNode = put(rootNode, key, value);
    }

    private Node put(Node x, K key, V value) {
        if (x == null) {
            size++;
            return new Node(key, value);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.leftSmaller = put(x.leftSmaller, key, value);
        } else if (cmp > 0) {
            x.rightBigger = put(x.rightBigger, key, value);
        } else {
            x.value = value;
        }
        return x;
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet(){
        return keySetHelper(rootNode, new HashSet<>());
    }

    private Set<K> keySetHelper(Node node, Set<K> keySet){
        if(node == null){
            return keySet;
        }

        keySetHelper(node.leftSmaller, keySet);
        keySet.add(node.key);
        keySetHelper(node.rightBigger, keySet);
        return keySet;
    }

    public Node findMax(){
        return findMax(rootNode);
    }
    private Node findMax(Node node){
        if(node == null){
            return null;
        }else if(node.rightBigger == null){
            return node;
        }
        return findMax(node.rightBigger);
    }

    public Node findMin(){
        return findMin(rootNode);
    }

    private Node findMin(Node node){
        if(node == null){
            return null;
        }else if(node.leftSmaller == null){
            return node;
        }
        return findMin(node.leftSmaller);
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key){
       if(!containsKey(key)){
           return null;
       }
        return null;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value){
        return null;
    }

    public static void main(String[] args){
        BSTMap<Integer, String> testMap = new BSTMap<>();
        String[] testString = {"k", "h", "y", "w", "a", "t", "i", "t"};
        int[] testInt = {5,1,3,4,2,7,9, -1};
        for(int i = 0; i < testString.length; i++){
            testMap.put(testInt[i], testString[i]);
        }
        System.out.println(testMap.findMax().key);
        System.out.println(testMap.findMin().key);
    }
}