public class OffByN implements CharacterComparator{

    public int offset;

    public OffByN(int n){
        this.offset = n;
    }

    @Override
    public boolean equalChars(char x, char y){
        int result = Math.abs(x-y);
        return result == this.offset;
    }
}