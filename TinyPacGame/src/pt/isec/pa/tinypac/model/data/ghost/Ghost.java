package pt.isec.pa.tinypac.model.data.ghost;

import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.pacman.PacMan;


/**
 * Classe abstrata Ghost que gere todas as instancias dos fantasmas:
 * esta classe será "rescrita" por todos os tipos de fantasmas necessários
 */
public abstract class Ghost implements  IGhost{

    protected int x,y;
    private int spawn_x , spawn_y;
    protected String name;
    protected int direction;
    protected int movement_speed;
    protected int vulnerability_time;
    protected boolean vulnerability;

    protected Ghost(int x, int y) {
        this.movement_speed = 1;
        this.vulnerability_time = 20;
        this.vulnerability = false;
        this.name = new String("Name");
        this.x = x;
        this.spawn_x = x;
        this.y = y;
        this.spawn_y = y;
        direction = UP;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName(){
        return name;
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

    public boolean getVulnerability(){
        return vulnerability;
    }

    public abstract void move(Maze maze , PacMan pacMan);

    public void reset(){
        x = spawn_x;
        y = spawn_y;
        vulnerability = false;
    }

    public void toggleVulnerability(boolean value){
        this.vulnerability = value;
    }

}
