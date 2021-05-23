package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 4;
    private static final double MAX_LF = 2;
    private ArrayMap<K, V>[] buckets;
    private int size;
    private static Random rand = new Random();;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    private int hash(K key, int numerBuckets) {
        if (key == null) {
            return 0;
        }

        return Math.floorMod(key.hashCode(), numerBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        isKeyValid(key);

        int index = hash(key);
        return buckets[index].get(key);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        isKeyValid(key);


        if(isLoadFactorTooBig()){
            resize();
        }

        int index = hash(key);
        if(containsKey(key)){
            buckets[index].put(key, value);
            return;
        }
        buckets[index].put(key, value);
        this.size++;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return this.size;
    }

    private void isKeyValid(K key){
        if(key == null){
            throw new IllegalArgumentException("Keys cannot be null!!!");
        }
    }

    private boolean isLoadFactorTooBig(){
        return loadFactor() > MAX_LF;
    }

    private void resize(){
        int doubleLength = this.buckets.length*2;
        ArrayMap<K, V>[] biggerBuckets = new ArrayMap[doubleLength];
        initializeBuckets(biggerBuckets);
        Set<K> allKey = keySet();
        int newIndex;
        for(K key: allKey){
            newIndex = hash(key, doubleLength);
            V keyValue = get(key);
            biggerBuckets[newIndex].put(key, keyValue);
        }
        this.buckets = biggerBuckets;
    }

    private void initializeBuckets(ArrayMap[] bucket){
        for(int i = 0; i < bucket.length; i++){
            bucket[i] = new ArrayMap<K,V>();
        }
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> allKey = new HashSet<>();
        for(int i = 0; i < buckets.length; i++){
            Set<K> eachBucket = buckets[i].keySet();
            for(K key: eachBucket){
                allKey.add(key);
            }
        }
        return allKey;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args){
        MyHashMap<Integer, Character> testMap = new MyHashMap<>();

        char c;
//        for(int i = 0; i < 40; i++){
//            c = (char) ('a' + rand.nextInt(26));
//            testMap.put(rand.nextInt(500), c);
//        }
        testMap.put(50, 'a');
        testMap.put(50,'b');

        Set<Integer> set = testMap.keySet();
        Character ca = testMap.get(50);
    }
}
