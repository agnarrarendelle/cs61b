public class UnionFind {

    // TODO - Add instance variables?
    private int[] parent;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        if(n <= 0){
            throw new IllegalArgumentException(n + " must be a positive integer");
        }

        this.parent = new int[n];
        for(int i = 0; i < this.parent.length; i++){
            this.parent[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if(vertex < 0 || vertex > parent.length){
            throw new RuntimeException(vertex + " is not a valid index");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
       validate(v1);
       while (parent(v1) >= 0){
           v1 = parent[v1];
       }
       return -1 * parent[v1];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return parent[v1];
    }



    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);

        int v1Parent = find(v1);
        int v2Parent = find(v2);

        return v1Parent == v2Parent;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a
       vertex with itself or vertices that are already connected should not
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);

        if(connected(v1,v2)){
            return;
        }

        int size1 = sizeOf(v1);
        int size2 = sizeOf(v2);



        if(size1 > size2){
            parent[v1] -= size2;
            parent[v2] = v1;
        }else{
            parent[v2] -= size1;
            parent[v1] = v2;
        }

    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);

        if(parent[vertex] < 0){
            return vertex;
        }else{
            parent[vertex] = find(parent[vertex]);
            return parent[vertex];
        }
    }

    public static void main(String[] args){
        UnionFind union = new UnionFind(6);
        int size0 = union.sizeOf(0);
        int root0 = union.find(0);

        union.union(0,5);
        union.union(2,5);
        union.union(3,4);

        union.union(5,4);
        union.union(4,0);
        int size = union.sizeOf(4);
    }
}