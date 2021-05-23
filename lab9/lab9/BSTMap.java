package lab9;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if(p == null){
            return null;
        }else if(key == p.key){
            return p.value;
        }

        int cmp = key.compareTo(p.key);
        if(cmp > 0){
            return getHelper(key, p.right);
        }else{
            return getHelper(key, p.left);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if(p == null){
            this.size++;
            return new Node(key, value);
        }

        int cmp = key.compareTo(p.key);
        if(cmp > 0){
            p.right = putHelper(key,value, p.right);
        }else if(cmp < 0){
            p.left = putHelper(key,value, p.left);
        }else{
            p.value = value;
        }

        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return this.size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    private boolean isContain(K key){
        return get(key) != null;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        if(isEmpty()){
            return null;
        }
        Node removedResult = remove(root, key);
        root = removedResult;
        return removedResult.value;
    }

    private Node remove(Node node, K key){
        if(node == null){
            return null;
        }

        int cmp = key.compareTo(node.key);

        if(cmp < 0){//in the right reee
            node.left = remove(node.left, key);
        }else if(cmp > 0){//in the left tree
            node.right = remove(node.right, key);
        }else{//find it
            if(node.right == null){
                return node.left;
            }
            if(node.left == null){
                return node.right;
            }

            Node temp = node;
            node = findMin(temp.right);
            node.right = removeMin(temp.right);
            node.left = temp.left;
        }

        return node;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    private K findMin(){
        if(isEmpty()){
            return null;
        }
        return findMin(root).key;
    }

    private Node findMin(Node node){
        if (node.left == null){
            return node;
        }
        return findMin(node.left);
    }

    private K findMax(){
        if(isEmpty()){
            return null;
        }
        return findMax(root).key;
    }

    private Node findMax(Node node){
        if(node.right == null){
            return node;
        }
        return findMax(node.right);
    }

    private void removeMin(){
        if(isEmpty()){
            return;
        }
        root = removeMin(root);
    }

    private Node removeMin(Node node){
        if(isEmpty()){
            return null;
        }
        if(node.left == null){
            this.size--;
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    private void removeMax(){
        if(isEmpty()){
            return;
        }
        root = removeMax(root);
    }

    private Node removeMax(Node node){

        if(node.right == null){
            this.size--;
            return node.left;
        }
        node.right = removeMax(node.right);
        return node;
    }


    private boolean isEmpty(){
        return this.size == 0;
    }

    private void printInOrder(){
        if(isEmpty()){
            return;
        }
        printInOrder(root);
    }

    private void printInOrder(Node node){
        if(node == null){
            return;
        }
        printInOrder(node.left);
        System.out.println(node.key);
        printInOrder(node.right);
    }

    public static void main(String[] args){
        BSTMap<Integer, String> testMap = new BSTMap<>();
        testMap.put(10, "a");
        testMap.put(15, "b");
        testMap.put(7,"r");
        testMap.put(12, "v");

        String removed = testMap.remove(10);
    }
}
