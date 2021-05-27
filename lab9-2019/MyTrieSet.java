import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class MyTrieSet<V> implements TrieSet61B{
    private static final int R = 256;
    private Node root;

    private static class Node{
       private char character;
       private boolean isKey;
       private Hashtable<Character, Node> next;
       private Node(char c, boolean isKey){
           this.character = c;
           this.isKey = isKey;
           this.next = new Hashtable<>();
       }
    }


    //Initialize the root witt a random character
    public MyTrieSet(){
        root = new Node('@', false);
    }

    private void isKeyValid(String key){
        if(key == null || key.length() < 1){
            throw new IllegalArgumentException("Key cannot be null!!");
        }
    }


    /** Clears all items out of Trie */
    @Override
    public void clear(){
        root.next.clear();
    }




    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key){
        isKeyValid(key);

        Node current = root;
        for(int i = 0; i < key.length(); i++){
            char ch = key.charAt(i);
            if(!current.next.containsKey(ch)){
                return false;
            }
            current = current.next.get(ch);
        }
        return true;
    }






    /** Inserts string KEY into Trie */
    @Override
    public void add(String key){
        isKeyValid(key);

        if(contains(key)){
            return;
        }
        add(root, key);
    }

    private void add(Node node, String key){
        //put the first character of the key into the tire
        char ch = key.charAt(0);
        if(!node.next.containsKey(ch)){
            node.next.put(ch, new Node(ch, false));
        }

        //recursively do the same to the rest of the string
        Node nextNode = node.next.get(ch);
        if(key.length() == 1){
            nextNode.isKey = true;
        }else{
            add(nextNode, key.substring(1));
        }
    }




    /** Returns a list of all words that start with PREFIX */
    /**
     * Returns all keys in the trie set.
     *
     * @param prefix the prefix string
     * @return the list of keys with the given prefix
     * @throws IllegalArgumentException if {@code key} is {@code null} or size is 0
     */

    public List<String> keysWithPrefix(String prefix) {
        if (prefix == null || prefix.length() < 1) {
            throw new IllegalArgumentException("argument to keysWithPrefix() is null or size is 0");
        }
        Node node = getEndNode(prefix);
        if (node == null) {
            return null;
        }
        ArrayList<String> keys = new ArrayList<>();
        for (Character ch : node.next.keySet()) {
            collect(prefix + ch, keys,node.next.get(ch));
        }
        return keys;
    }

    /**
     * Helper function to get the last node of the given {@code key}.
     *
     * @param key the key
     * @return the last node of the given {@code key}.
     */
    private Node getEndNode(String key) {
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (!curr.next.containsKey(ch)) {
                return null;
            }
            curr = curr.next.get(ch);
        }
        return curr;
    }

    public List<String> collect(){
        if(root == null){
            return null;
        }
        List<String> allKeys = new ArrayList<>();

        for(char ch: root.next.keySet()){
            collect(String.valueOf(ch), allKeys, root.next.get(ch));
        }
        return allKeys;
    }

    private void collect(String k, List<String> collectedString, Node node){
        if(node.isKey){
            collectedString.add(k);
        }

        for(Character ch: node.next.keySet()){
            collect(k+ch, collectedString, node.next.get(ch));
        }
    }




    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key){
        throw new UnsupportedOperationException();


    }
}