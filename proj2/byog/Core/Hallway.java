package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hallway {
//1.directky connected
    private static final int maxWidth = 3;
    public List<Position> randomPositionsInsideEachRoom = new ArrayList<>();

    public static void drawHallways(){




    }



    private boolean isCanDirectlyConnected(Position pos){
        return true;
    }



    @Test
    public void testChooseRandomPosInVerticalSides(){
        Position roomPos = new Position(10,10);
        Rooms testRoom = new Rooms(roomPos, 10,10);
        Position leftTop = new Position(10, 19);
        Position rightTop = new Position(19,19);


    }
}