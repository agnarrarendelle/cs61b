/** A SLList is a list of integers, which hides the terrible truth
    of the nakedness within */

public class SLList<ElemType>{

    private class StuffNode{
        public ElemType item;
        public StuffNode next;
    
        public StuffNode(ElemType i, StuffNode n){
            item = i;
            next = n;
        }
    }
    /** The first item of a list(if it exists) is at sentinel.next*/
    private StuffNode sentinel;
    private int size;


    // public SLList(){
    //     /**item value of the sentinel node can be any value since 
    //      * we are never gonna look into it */
    //     this.sentinel = new StuffNode(0, null);
    //     this.size = 0;
    // }

    public SLList(ElemType x){
        this.sentinel = new StuffNode(x, null);
        this.size++;
    }
    
    /** Adds x to the front of the list */
    public void addFirst(ElemType x){
        sentinel.next = new StuffNode(x, sentinel.next);
        this.size++;
    }

    public ElemType getFirst(){
        return this.sentinel.next.item;
    }

    public void addLast(ElemType x){
        // this.size++;
        // this.last.next = new StuffNode(x, null);
        // this.last = this.last.next;

        this.size++;
        StuffNode p = this.sentinel;
        /** Move p to the end of the list */
        while(p.next != null){
            p = p.next;
        }
        p.next = new StuffNode(x, null);

    }
    
    public int size(){
        return this.size;
    }

    // public static void main(String[] args){
    //     /** Creates a list of one integer 10 */
    //     SLList L = new SLList();
    //     L.addLast(5);
    //     L.addFirst(1);
    //     System.out.println(L.size());  
    // }
}