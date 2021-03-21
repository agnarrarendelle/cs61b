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

    private IntNode first;
    private int size;


    public SLList(int x){
        first = new IntNode(x, null);
        this.size = 1
    }
    
    /** Adds x to the front of the list */
    public void addFirst(int x){
        first = new IntNode(x, first);
        this.size++;
    }

    public int getFirst(){
        return this.first.item;
    }

    public void addLast(int x){
        this.size++;
        IntNode p = this.first;
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
        return size(this.first);
    }

    public static void main(String[] args){
        /** Creates a list of one integer 10 */
        SLList L = new SLList(15);
        L.addFirst(10);
        L.addFirst(5);
        L.addLast(20);
        System.out.println(L.size());  
    }
}