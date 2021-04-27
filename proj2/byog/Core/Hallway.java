package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hallway {
//1.directky connected
    private static final int maxWidth = 3;

    public static void drawHallways(){

        for(int i = 0; i < Rooms.randomPositionInsideEachRoom.size()-1; i++){
            Position currentPos = Rooms.randomPositionInsideEachRoom.get(i);
            Position nextPos = Rooms.randomPositionInsideEachRoom.get(i+1);
            connectTwoRooms(currentPos, nextPos);
        }


    }

    private static void connectTwoRooms(Position smallerX, Position biggerX){
        //first print a horizontal rect
//        for(int x = smallerX.X; x < smallerX.X+biggerX.X - smallerX.X; x++){
//            for(int y = smallerX.Y; y < smallerX.Y + maxWidth; y++){
//                if(World.world[x][y] == Tileset.NOTHING || World.world[x][y] == World.wallTexture){
//                    World.world[x][y] = Tileset.WATER;
//                }
//            }
//        }


        if(smallerX.X == biggerX.X){
            directlyConnect(smallerX, biggerX);
        }else{
            //LLLShapeConnect(smallerX, biggerX);
        }
    }

    private static void directlyConnect(Position pos1, Position pos2){
        Position above = pos1;
        Position below = pos2;

        if(isOnePosAboveAnother(pos1, pos2)){
            above = pos2;
            below = pos1;
        }

        int height = above.Y - below.Y;

        for(int y = below.Y; y <= below.Y + height; y++ ){
            if(World.world[above.X][y] == Tileset.WALL){

            World.world[above.X][y] = Tileset.WATER;
            }
        }
    }

    //Check if pos2.Y is greater than pos1.Y
    private static boolean isOnePosAboveAnother(Position pos1, Position pos2){
        return pos2.Y > pos1.Y;
    }



    @Test
    public void testChooseRandomPosInVerticalSides(){
        Position roomPos = new Position(10,10);
        Rooms testRoom = new Rooms(roomPos, 10,10);
        Position leftTop = new Position(10, 19);
        Position rightTop = new Position(19,19);


    }
}