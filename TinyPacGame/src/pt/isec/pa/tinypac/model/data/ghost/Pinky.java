package pt.isec.pa.tinypac.model.data.ghost;

import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.pacman.PacMan;

public class Pinky extends Ghost{

    public Pinky(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(Maze maze, PacMan pacMan) {

    }

    @Override
    public String toString() {
        return "Pinky{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                ", movement_speed=" + movement_speed +
                ", vulnerability_time=" + vulnerability_time +
                '}';
    }

}
