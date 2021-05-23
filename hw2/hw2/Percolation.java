package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.HashSet;
import java.util.Set;

public class Percolation {
    private class Position{
        public int x;
        public int y;
        public Position(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    public boolean world[][];
    public  WeightedQuickUnionUF WQF;
    public WeightedQuickUnionUF fullnessWQF;
    public int sideLength;
    private int numberOfOpenedPos;
    private int virtualTop;
    private int virtualBottom;
    private byte posStatus[];

    public Percolation(int N){
        if(N < 1){
            throw new IllegalArgumentException("N must be at least 1");
        }

        this.sideLength = N;
        this.world = new boolean[N][N];
        this.WQF = new WeightedQuickUnionUF(N*N + 2);
        this.fullnessWQF = new WeightedQuickUnionUF(N*N + 1);
        this.numberOfOpenedPos = 0;
        this.virtualTop = N*N;
        this.virtualBottom = N*N + 1;
        this.posStatus = new byte[N*N];

        for(int i = 0; i < N; i++){
            for(int k = 0; k < N; k++){
                this.world[i][k] = false;
            }
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col){
        isIllegalPos(row, col);
        if(isOpened(row, col)){
            return;
        }

        this.numberOfOpenedPos++;
        this.world[row][col] = true;
        int posId = xyToId(row, col);

        //check if the opened position is at top or bottom and connect it to the virtual spot if it is
        if(isAtTopLine(col)){
            WQF.union(posId, virtualTop);
            fullnessWQF.union(posId, virtualTop);
        }
        if(isAtBottomLine(col)){
            WQF.union(posId, virtualBottom);
        }

        Set<Position> neighborPosSet = neighborPos(new Position(row,col));
        for(Position pos: neighborPosSet){
            if(isOpened(pos.x, pos.y)){
                WQF.union(xyToId(pos.x, pos.y), posId);
                fullnessWQF.union(xyToId(pos.x, pos.y), posId);
            }
        }
    }



    private boolean isAtTopLine(int y){
        return y == 0;
    }

    private boolean isAtBottomLine(int y){
        return y == (this.sideLength - 1);
    }

    public void isIllegalPos(int row, int col){
        if(row >= this.sideLength || col >= this.sideLength){
            throw new IllegalArgumentException("Row and Columns can at most be N-1");
        }else if(row < 0 || col < 0){
            throw new IllegalArgumentException("Row and Columns must be greater or equal to 0");
        }
    }

    public Set neighborPos(Position pos){
        Set<Position> neighborPosSet = new HashSet<>();
        //add top and bottom
        int offset;
        offset = 1;
        for(int i = 0; i < 2; i++){
            if(isPosInWorld(pos.y + offset)){
                neighborPosSet.add(new Position(pos.x, pos.y + offset));
            }
            offset -= 2;
        }

        offset = 1;
        for(int k = 0; k < 2; k++){
            if(isPosInWorld(pos.x + offset)){
                neighborPosSet.add(new Position(pos.x + offset, pos.y));
            }
            offset -= 2;
        }

        return neighborPosSet;
    }



    private boolean isPosInWorld(int pos){
        return (pos >= 0) && (pos < this.sideLength);
    }

    //trans the (X,Y) coordinate to their corresponding ID
    private int xyToId(int x, int y){
        isIllegalPos(x, y);

        return y + (x*this.sideLength);
    }

    // is the site (row, col) open?
    public boolean isOpened(int row, int col){
        isIllegalPos(row, col);

        return this.world[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if(isOpened(row, col)){
            return fullnessWQF.connected(xyToId(row, col), virtualTop);
        }
        return false;
    }


    // number of open sites
    public int numberOfOpenSites(){
        return this.numberOfOpenedPos;
    }

    // does the system percolate?
    public boolean percolates(){
        return WQF.connected(virtualTop, virtualBottom);
    }

    public static void main(String[] args){
        Percolation testPerco = new Percolation(3);
        testPerco.open(0,2);
        testPerco.open(0,1);
        testPerco.open(0,0);

        testPerco.open(2,1);
        testPerco.open(2,2);
        testPerco.open(1,1);



        System.out.println(testPerco.isFull(2,2));
    }
}
