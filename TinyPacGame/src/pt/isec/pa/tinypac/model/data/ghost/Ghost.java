package pt.isec.pa.tinypac.model.data.ghost;

import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.pacman.PacMan;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Classe abstrata Ghost que gere todas as instancias dos fantasmas:
 * esta classe será "rescrita" por todos os tipos de fantasmas necessários
 */
public abstract class Ghost implements  IGhost , Serializable {
    static final long serialVersionUID = 1L;

    protected int x,y;
    private int spawn_x , spawn_y;
    protected String name;
    protected int direction;
    protected int movement_speed;
    protected int vulnerability_time;
    protected boolean vulnerability;
    protected boolean returning;
    protected ArrayList<Integer> moves;

    protected Ghost(int x, int y) {
        this.movement_speed = 1;
        this.vulnerability_time = 20;
        this.vulnerability = false;
        this.returning = false;
        this.name = new String("Name");
        this.moves = new ArrayList<>();
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

    public int getSpawn_x() {
        return spawn_x;
    }

    public int getSpawn_y() {
        return spawn_y;
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

    /**
     * Função responsavel por mover os fantasmas,
     * esta função e abstrata pois cada tipo de fantasmas vai ter o seu movimento especifico
     * @param maze
     * @param pacMan
     */
    public abstract void move(Maze maze , PacMan pacMan);

    protected boolean returning_moves(){

        if(returning){
            int lastMove = moves.get(moves.size()-1);
            moves.remove(moves.size()-1);

            if(lastMove == UP){
                y++;
            }else if(lastMove == RIGHT){
                x--;
            }else if(lastMove == LEFT){
                x++;
            } else if (lastMove == DOWN) {
                y--;
            }

            if(this.x == getSpawn_x() && this.y == getSpawn_y()){
                returning = false;
                System.out.println(returning);
            }

            return true;
        }

        return false;
    }


    public void reset(){
        returning = true;
        vulnerability = false;
    }

    public void toggleVulnerability(boolean value){
        this.vulnerability = value;
    }

}
