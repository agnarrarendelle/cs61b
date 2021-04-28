package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Player {

    public static void printPlayer(){
        int randomX;
        int randomY;
        Position pos;

        do {
            randomX = Game.random.nextInt(Game.WIDTH);
            randomY = Game.random.nextInt(Game.HEIGHT);

            pos = new Position(randomX, randomY);
        }while(!isPlayerPosOk(pos));

        World.fillPosWithTexture(new Position(pos.X, pos.Y), Tileset.PLAYER);
    }

    private static boolean isPlayerPosOk(Position pos){
        return World.isPosATexture(pos, World.roomTexture);
    }
}