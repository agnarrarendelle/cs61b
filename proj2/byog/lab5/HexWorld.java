package byog.lab5;

import edu.princeton.cs.algs4.ST;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.awt.*;
import java.util.Random;
import byog.Core.RandomUtils;

import javax.naming.ldap.Rdn;
import javax.swing.text.Position;

public class HexWorld {
    public static class Position{
        public int x;
        public int y;

        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Adds a hexagon to the world.
     * @param world the world to draw on
     * @param pos the bottom left coordinate of the hexagon
     * @param size the size of the hexagon
     * @param type the tile to draw
     */
    public static void addHexagon(TETile[][] world, Position pos, int size, TETile type){
        if(size < 2){
            throw new IllegalArgumentException("Hexagon must be at least size 2");
        }else{
            //A hexagon have 2*size rows, the code below iterates from bottom to top
            for(int i = 0; i < size*2; i++){
                //choose the Y coordinate of the row
                int rowY = pos.y + i;
                //choose the X coordinate of the row
                int rowX = pos.x + hexRowOffset(size,i);
                //choose the position at which the row starts
                Position rowStartPos = new Position(rowX, rowY);
                //choose the row width
                int rowWidth = hexRowWidth(size,i);
                //add the rows
                addRow(world, rowStartPos, rowWidth, type);
            }

        }
    }

    public static int hexRowOffset(int size, int level){
        int offset = level;
        if(level >= size){
            offset = (2 * size) - offset - 1;
        }

        return -offset;
    }

    public static int hexRowWidth(int size, int i){
        int width = i;
        if(i >= size){
            width = (2*size) - width - 1;
        }

        return size + (2*width);
    }

    /** Adds a row of the same tile.
     * @param world the world to draw on
     * @param pos the leftmost position of the row
     * @param width the number of tiles wide to draw
     * @param type the tile to draw
     */
    public static void addRow(TETile[][] world, Position pos, int width, TETile type){
        for(int j = 0; j < width; j++){
            int xCoordinate = pos.x + j;
            int yCoordinate = pos.y;
            Random r = new Random();
            world[xCoordinate][yCoordinate] = TETile.colorVariant(type,32,32,32,r);
        }
    }
    
    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(90, 90);
        Position p = new Position(40,50);
        TETile t = Tileset.WALL;

        TETile[][] world = new TETile[90][90];
        for (int x = 0; x < 90; x += 1) {
            for (int y = 0; y < 90; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        addHexagon(world, p, 6, t);

        ter.renderFrame(world);
    }

//    @Test
//    public void testHexRowWidth() {
//        assertEquals(3, hexRowWidth(3, 5));
//        assertEquals(5, hexRowWidth(3, 4));
//        assertEquals(7, hexRowWidth(3, 3));
//        assertEquals(7, hexRowWidth(3, 2));
//        assertEquals(5, hexRowWidth(3, 1));
//        assertEquals(3, hexRowWidth(3, 0));
//        assertEquals(2, hexRowWidth(2, 0));
//        assertEquals(4, hexRowWidth(2, 1));
//        assertEquals(4, hexRowWidth(2, 2));
//        assertEquals(2, hexRowWidth(2, 3));
//    }
//
//    @Test
//    public void testHexRowOffset() {
//        assertEquals(0, hexRowOffset(3, 5));
//        assertEquals(-1, hexRowOffset(3, 4));
//        assertEquals(-2, hexRowOffset(3, 3));
//        assertEquals(-2, hexRowOffset(3, 2));
//        assertEquals(-1, hexRowOffset(3, 1));
//        assertEquals(0, hexRowOffset(3, 0));
//        assertEquals(0, hexRowOffset(2, 0));
//        assertEquals(-1, hexRowOffset(2, 1));
//        assertEquals(-1, hexRowOffset(2, 2));
//        assertEquals(0, hexRowOffset(2, 3));
//    }
}