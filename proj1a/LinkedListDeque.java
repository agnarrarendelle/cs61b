public class LinkedListDeque<ElemType>{
    public class StuffNode{
        public ElemType item;
        public StuffNode next;
        public StuffNode prev;

        public StuffNode(ElemType i, StuffNode p, StuffNode n){
            item = i;
            next = n;
            prev = p;
        }
    }

    private StuffNode sentinel;
    private int size;

    //Constructor function
    public LinkedListDeque(){
        this.sentinel = new StuffNode(null, null, null);
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
        this.size = 0;
    }

    public LinkedListDeque(LinkedListDeque other){
        this.sentinel = new StuffNode(null, null, null);
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
        this.size = 0;

        for(int i = 0; i < other.size(); i++){
            addLast((ElemType) other.get(i));
        }
    }

    public void addFirst(ElemType x){
        this.sentinel.next = new StuffNode(x, this.sentinel, this.sentinel.next);
        //this.sentinel.next.next.prev = sentinel node
        this.sentinel.next.next.prev = this.sentinel.next;
        this.size++;
    }

    public ElemType removeFirst(){
        if(this.size == 0){
            return null;
        }
        StuffNode removedNode = this.sentinel.next;
        this.sentinel.next.next.prev = this.sentinel;
        this.sentinel.next = this.sentinel.next.next;
        this.size--;
        return removedNode.item;
    }

    public void addLast(ElemType x){
        //this.sentinel.prev is now pointing at the last node
        this.sentinel.prev = new StuffNode(x, this.sentinel.prev ,this.sentinel);
        this.sentinel.prev.prev.next = this.sentinel.prev;
        this.size++;
    }

    public ElemType removeLast(){
        if(this.size == 0){
            return null;
        }

        StuffNode removedNode = this.sentinel.prev;
        this.sentinel.prev.prev.next = this.sentinel;
        this.sentinel.prev = this.sentinel.prev.prev;
        this.size--;
        return removedNode.item;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public int size(){
        return this.size;
    }

    public ElemType get(int index){
        if(index >= this.size){
            return null;
        }

        StuffNode ptr = this.sentinel;
        for(int i = 0; i <= index; i++){
            ptr = ptr.next;
        }
        return ptr.item;
    }

    public ElemType getRecursive(int index){
       if(index >= this.size || index < 0){
           return null;
       }

       int count = 0;
       StuffNode node = this.sentinel.next;
       return getRecursiveHelper(index, count, node);

    }

    private ElemType getRecursiveHelper(int index,int count, StuffNode node){
        if(index == count){
            return node.item;
        }else{
            return getRecursiveHelper(index, ++count, node.next);
        }
    }

    public void printDeque(){
        StuffNode ptr = this.sentinel;
        if(ptr.next != null){
            printDequeHelper(ptr.next);
            System.out.println();
        }
    }
    private void printDequeHelper(StuffNode node){
        if(node != this.sentinel){
            System.out.print(node.item);
            System.out.print(" ");
            printDequeHelper(node.next);
        }
    }

    public static void main(String[] args){
        LinkedListDeque<Integer> K = new LinkedListDeque<>();
        K.addFirst(6);
        K.addFirst(1);
        K.addLast(4);

        LinkedListDeque<Integer> J = new LinkedListDeque<>(K);
        System.out.println(J.size());
    }

}