package pt.isec.pa.tinypac.model.data.ghost;

import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.pacman.PacMan;

public abstract class Ghost implements  IGhost{

    protected int x,y;
    protected int direction;
    protected int movement_speed;
    protected int vulnerability_time;

    protected Ghost(int x, int y) {
        this.movement_speed = 1;
        this.vulnerability_time = 20;
        this.x = x;
        this.y = y;
        direction = UP;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setMovement_speed(int movement_speed) {
        this.movement_speed = movement_speed;
    }

    public void setVulnerability_time(int value){
        this.vulnerability_time = value;
    }

    public int getVulnerability_time(){
        return this.vulnerability_time;
    }

    public abstract void move(Maze maze , PacMan pacMan);


}
