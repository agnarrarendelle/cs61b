package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
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
                    world[x][y] = Tileset.NOTHING;
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

    public static void initializeWorld(){
        for (int x = 0; x < Game.WIDTH; x++) {
            for (int y = 0; y < Game.HEIGHT; y++) {
                world[x][y] = Tileset.WALL;//Choose the type of tiles to cover the window
            }
        }
    }

    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(Game.WIDTH, Game.HEIGHT);

        initializeWorld();

        for(int i = 0 ; i < 25; i++){
            addRoom();
        }
        printRooms();

        Position pos = new Position(10,10);


        ter.renderFrame(world);
    }
}