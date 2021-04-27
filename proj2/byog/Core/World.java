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
    public static final TETile roomTexture = Tileset.FLOOR;
    private static final int colorDifference = 64;
    public static final TETile wallTexture = Tileset.WALL;
    public static final TETile hallwayTexture = Tileset.WATER;

    public static final int maxNumOfRooms = 45;
    public static final int minNumOfRooms = 25;

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
                        int color = Game.random.nextInt(62);
                        world[x][y] = Tileset.WALL;
                    }else{
                        world[x][y] = roomTexture;
                    }
                }
            }
        }

        //sort the random Position in each room by their X value after printing all rooms

    }

    public static void printRoomsWithPos(Position pos, int width, int height){
            for(int x = pos.X; x < pos.X + width; x++){
                for(int y = pos.Y; y < pos.Y + height; y++){
                    world[x][y] = Tileset.NOTHING;
                }
            }
    }

    public static void addRandomNumberOfRooms(){

        int maxNumOfRooms = Game.random.nextInt(World.maxNumOfRooms - World.minNumOfRooms) + World.minNumOfRooms;

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

    public static void printRandomPosInRoom(){
        for(int i = 0; i < Rooms.randomPositionInsideEachRoom.size(); i++){
            Position eachRoomPos = Rooms.randomPositionInsideEachRoom.get(i);
            int x = eachRoomPos.X;
            int y = eachRoomPos.Y;
            world[x][y] = Tileset.SAND;
        }
    }

    public static void printHallways(){
        Hallway.drawHallways();
    }

    public static void printDoor(){
        Door.printDoor();
    }

    public static void printPlayer(){
        Player.printPlayer();
    }

    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(Game.WIDTH, Game.HEIGHT);

        initializeWorld();

        addRandomNumberOfRooms();


        printRooms();
        //printRandomPosInRoom();
        printHallways();

        printDoor();
        printPlayer();

        ter.renderFrame(world);
    }
}