public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r){
        first = f;
        rest = r;
    }

    public int size(){
        if(rest == null){
            return 1;
        }else{
            return 1 + this.rest.size();
        }
    }

    public static void main(String[] args) {
        IntList L = new IntList(15,null);
        L = new IntList(10, L);//create a new IntList where the rest is the old IntList
        L = new IntList(5, L);
        System.out.print(L.size());
    }
}