public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r){
        first = f;
        rest = r;
    }

    public int size(){
        if(this.rest == null){
            return 1;
        }else{
            return 1 + this.rest.size();
        }
    }

    public int iterativeSize(){
        int totalSize = 0;
        IntList p = this;
        while(p != null){
            totalSize++;
            p = p.rest;
        }
        return totalSize;
    }
    /** Return the ith item from the IntList */
    public int get(int i){
        if(i == 0){
            return this.first;
        }else{
            return this.rest.get(--i);
        }
    }

    public int getIteratively(int i){
        IntList p = this;
        while(i != 0){
            i--;
            p = p.rest;
        }
        return p.first;
    }

    public static void main(String[] args) {
        IntList L = new IntList(15,null);
        L = new IntList(10, L);//create a new IntList where the rest is the old IntList
        L = new IntList(5, L);
        System.out.print(L.getIteratively(1));
    }
}