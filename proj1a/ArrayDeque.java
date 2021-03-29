public class ArrayDeque<ElemType> {
    public ElemType[] items;
    public int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        this.items = (ElemType[]) new Object[8];
        this.size = 0;
        //nextFirst is at the end of the array and nextLast is at the beginning of the array
        this.nextFirst = this.items.length-1;
        this.nextLast = 0;
    }

    public ArrayDeque(ArrayDeque other){
        ElemType[] arr = (ElemType[]) new Object[other.items.length];
        System.arraycopy(other.items,0,arr,0, other.nextLast);
        int length = other.items.length - other.nextFirst - 1;
        System.arraycopy(other.items, other.nextFirst+1, arr, other.nextFirst+1, length);
        this.items = arr;
    }

    public void addFirst(ElemType x){
        if(this.nextFirst-1 == this.nextLast){
            this.resize(this.items.length*2);
        }
        this.items[this.nextFirst] = x;
        this.nextFirst--;
        this.size++;


    }

    public ElemType removeFirst(){
        if(this.nextFirst == this.items.length - 1){
            return null;
        }
        this.nextFirst++;
        ElemType temp = this.items[this.nextFirst];
        this.items[this.nextFirst] = null;
        this.size--;
        if(this.items.length >= 16 && this.size() < this.items.length/4){
            this.resize(this.items.length/2);
        }
        return temp;
    }

    public void addLast(ElemType x){
        if(this.nextFirst-1 == this.nextLast){
            this.resize(this.items.length*2);
        }
        this.items[this.nextLast] = x;
        this.nextLast++;
        this.size++;
    }

    public ElemType removeLast(){
        if(this.nextLast == 0){
            return null;
        }

        this.nextLast--;
        ElemType temp = this.items[this.nextLast];
        this.items[this.nextLast] = null;
        this.size--;

        if(this.items.length >= 16 && this.size() < this.items.length/4){
            this.resize(this.items.length/2);
        }
        return temp;
    }

    public int size(){
        return this.size;
    }

    public ElemType get(int index){
        int firstLength = this.items.length - this.nextFirst - 1;
        int lastLength = this.nextLast;
        if(index >= firstLength + lastLength){
            return null;
        }

        if(index < firstLength){
            return this.items[this.nextFirst+1+index];
        }else{
            index -= firstLength;
            return this.items[index];
        }
    }

    public void printDeque(){
        for(int i = this.nextFirst+1; i < this.items.length; i++){
            System.out.print(this.items[i]);
            System.out.print(" ");
        }

        for(int i = 0; i < this.nextLast; i++ ){
            System.out.print(this.items[i]);
            System.out.print(" ");
        }
        System.out.println();
    }


    public void resize(int capacity){
        ElemType[] arr = (ElemType[]) new Object[capacity];
        System.arraycopy(this.items, 0, arr, 0, this.nextLast);//copy lasts
        int length = this.items.length - this.nextFirst - 1;
        System.arraycopy(this.items, this.nextFirst+1, arr, capacity - length, length);
        this.nextFirst = capacity - length - 1;
        this.items = arr;
    }

    public static void main(String[] args){
        ArrayDeque<Integer> arr = new ArrayDeque<>();

        arr.addFirst(3);
        arr.addFirst(2);
        arr.addFirst(1);
        arr.addFirst(0);
        arr.addFirst(-1);

        arr.addLast(95);
        arr.addLast(96);
        arr.addLast(97);
        arr.addLast(98);
        arr.addLast(99);
        int k = arr.get(9);
    }

}