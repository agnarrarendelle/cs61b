/** A SLList is a list of integers, which hides the terrible truth
    of the nakedness within */

public class SLList{

    private static class IntNode{
        public int item;
        public IntNode next;
    
        public IntNode(int i, IntNode n){
            item = i;
            next = n;
        }
    }
    /** The first item of a list(if it exists) is at sentinel.next*/
    private IntNode sentinel;
    private int size;


    public SLList(){
        /**item value of the sentinel node can be any value since 
         * we are never gonna look into it */
        this.sentinel = new IntNode(0, null);
        this.size = 0;
    }
    
    /** Adds x to the front of the list */
    public void addFirst(int x){
        sentinel.next = new IntNode(x, sentinel.next);
        this.size++;
    }

    public int getFirst(){
        return this.sentinel.next.item;
    }

    public void addLast(int x){
        this.size++;
        IntNode p = this.sentinel;
        /** Move p to the end of the list */
        while(p.next != null){
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }
    /** Returns the size of the list at IntNode p(a helper function) */
    private static int size(IntNode p){
        if(p.next == null){
            return 1;
        }else{
            return 1 + size(p.next);
        }
    }

    public int size(){
        return this.size;
    }

    public static void main(String[] args){
        /** Creates a list of one integer 10 */
        SLList L = new SLList();
        L.addLast(5);
        L.addFirst(1);
        System.out.println(L.size());  
    }
}