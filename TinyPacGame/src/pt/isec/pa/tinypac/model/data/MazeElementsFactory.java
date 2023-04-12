package pt.isec.pa.tinypac.model.data;
import pt.isec.pa.tinypac.model.data.elements.*;

public class MazeElementsFactory {

    public static IMazeElement create (char ch){

        IMazeElement ret = null;

        switch (ch){
            case 'x' -> ret = new Wall();
            case 'W' -> ret = new WrapZone();
            case 'o' -> ret = new BallElement();
            case 'F' -> ret = new FruitElement();
            case 'M' -> ret = new PacManSpawn();
            case 'O' -> ret = new BigBallElement();
            case 'Y' -> ret = new GhostSpawn();
            case 'y' -> ret = new GhostSpawnCave();
        }

        return ret;
    }

    private MazeElementsFactory() {}

}
