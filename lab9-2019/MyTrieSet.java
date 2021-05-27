import java.util.List;

public class MyTrieSet<V> implements TrieSet61B{
    private static final int R = 256;
    private Node root;

    private static class Node{
        private boolean isKey;
        private Node[] next ;

        public Node(){
            isKey = false;
            next = new Node[R];
        }
    }

    public MyTrieSet(){

    }

    private void isKeyValid(String key){
        if(key == null || key.length() < 1){
            throw new IllegalArgumentException("Key cannot be null!!");
        }
    }


    /** Clears all items out of Trie */
    @Override
    public void clear(){
        root = null;
    }




    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key){
       return get(root, key, 0) != null;
    }



    private Node get(Node node, String key, int count){
        if(node == null){
            return null;
        }
        if(count == key.length()){
            return node;
        }

        char c = key.charAt(count);
        return get(node.next[c], key, count+1);
    }


    /** Inserts string KEY into Trie */
    @Override
    public void add(String key){
        isKeyValid(key);

        root = add(root, key, 0);
    }

    private Node add(Node node, String key, int count){
        if(node == null){
            node = new Node();
        }
        if(count == key.length()){
            node.isKey = true;
            return node;
        }

        char c = key.charAt(count);
        node.next[c] = add(node.next[c], key, count+1);
        return node;
    }


    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix){
        throw new UnsupportedOperationException();
    }

    private void collect(List collected, String key, Node node){

    }


    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key){
        throw new UnsupportedOperationException();


        fdssdfd
    }
}