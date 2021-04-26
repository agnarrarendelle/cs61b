package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class World {
    public static TETile[][] world = new TETile[Game.WIDTH][Game.HEIGHT];
    public static List<Rooms> roomList = new ArrayList<>();

    private static void addRoom(){
        Rooms newRooom = new Rooms();
        roomList.add(newRooom);
    }

    public static void printRooms(){

        for(int i = 0; i < roomList.size(); i++){
            Rooms eachRoom = roomList.get(i);
            for(int x = eachRoom.pos.X; x < eachRoom.pos.X + eachRoom.width; x++){
                for(int y = eachRoom.pos.Y; y < eachRoom.pos.Y + eachRoom.height; y++){
                    boolean isXSidesEdge = (x == eachRoom.pos.X || x == eachRoom.pos.X + eachRoom.width-1);
                    boolean isYSidesEdge = (y == eachRoom.pos.Y || y == eachRoom.pos.Y + eachRoom.height-1);
                    if(isXSidesEdge || isYSidesEdge){
                        //world[x][y] = Tileset.WALL;
                        int color = Game.random.nextInt(32);
                        world[x][y] = TETile.colorVariant(Tileset.WALL, color,color,color, Game.random);
                    }else{
                        world[x][y] = Tileset.NOTHING;
                    }
                }
            }
        }
    }

    public static void printRoomsWithPos(Position pos, int width, int height){
            for(int x = pos.X; x < pos.X + width; x++){
                for(int y = pos.Y; y < pos.Y + height; y++){
                    world[x][y] = Tileset.NOTHING;
                }
            }
    }

    public static void addRandomNumberOfRooms(){
        int minNum = 15;
        int maxNum = 35;
        int maxNumOfRooms = Game.random.nextInt(maxNum - minNum) + minNum;

        for(int i = 0; i < maxNumOfRooms; i++){
            addRoom();
        }
    }

    public static void initializeWorld(){
        for (int x = 0; x < Game.WIDTH; x++) {
            for (int y = 0; y < Game.HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;//Choose the type of tiles to cover the window
            }
        }
    }

    public static void printHallways(){
        for(int i = 0; i < Rooms.randomPositionInsideEachRoom.size(); i++){
            Position eachRoomPos = Rooms.randomPositionInsideEachRoom.get(i);
            int x = eachRoomPos.X;
            int y = eachRoomPos.Y;
            world[x][y] = Tileset.SAND;
        }
    }

    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(Game.WIDTH, Game.HEIGHT);

        initializeWorld();

        addRandomNumberOfRooms();
        printRooms();
        printHallways();

        Collections.sort(Rooms.randomPositionInsideEachRoom);
        for(int i = 0; i < Rooms.randomPositionInsideEachRoom.size(); i++){
            Position pos = Rooms.randomPositionInsideEachRoom.get(i);
            System.out.print(pos.X +" | ");
        }

        ter.renderFrame(world);
    }
}