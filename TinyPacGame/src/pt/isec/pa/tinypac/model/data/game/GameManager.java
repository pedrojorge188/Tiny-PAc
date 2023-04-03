package pt.isec.pa.tinypac.model.data.game;
import pt.isec.pa.tinypac.model.data.Balls.Ball;
import pt.isec.pa.tinypac.model.data.Balls.BigBall;
import pt.isec.pa.tinypac.model.data.Fruit.Fruit;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.game.interfaces.GameConsts;
import pt.isec.pa.tinypac.model.data.ghost.*;
import pt.isec.pa.tinypac.model.data.pacman.PacMan;

import java.util.*;

public class GameManager implements GameConsts {

    private int level;
    private int stage;
    private int points;
    private Maze current_maze;
    private Set<Ghost> ghost_list;
    private PacMan pacman;
    private Fruit fruit;
    private Set <Ball> balls_list;

    public GameManager(){

        this.level = 1;
        this.stage = 1;
        this.points = 0;
        this.current_maze = GameLevel.getLevel(this.stage).getValue2();
        ghost_list = new HashSet<>();
        balls_list = new HashSet<>();
        pacman = null;
        fruit = null;

    }

    public boolean fillGame(){

        ghost_list.clear();
        balls_list.clear();

        char [][] tmp = current_maze.getMaze();

            for (int i = 0; i < this.getMazeRows(); i++) {
                for (int j = 0; j < this.getMazeCols(); j++) {
                    if(tmp[i][j] == SPAWN2){

                        ghost_list.add(new Inky(i,j));
                        ghost_list.add(new Pinky(i,j));
                        ghost_list.add(new Blinky(i,j));
                        ghost_list.add(new Clyde(i,j));

                        for(Ghost e : ghost_list) {
                            e.setMovement_speed(this.level *1);
                            e.setVulnerability_time(e.getVulnerability_time()-this.level);
                        }

                    }else if(tmp[i][j] == SPAWN){

                        pacman = new PacMan(i,j);

                    }else if(tmp[i][j] == BALL){

                        balls_list.add(new Ball(i,j));

                    }else if(tmp[i][j] == BIG_BALL){

                        balls_list.add(new BigBall(i,j));

                    }else if(tmp[i][j] == FRUIT){

                        fruit = new Fruit(i,j);

                    }
                }

        }

        //debug
        for(Ghost e : ghost_list){
            System.out.println(e);
        }
        for(Ball e : balls_list){
            System.out.println(e);
        }
        System.out.println(pacman);
        System.out.println(fruit);

        return true;
    }

    public boolean setLevel(int level) {

        if(level > 20 || level < 0)
            return false;

        this.level = level;

        if(!GameLevel.getLevel(level).getValue1()){
            return false;
        }

        this.stage = level;
        current_maze = GameLevel.getLevel(level).getValue2();

        return true;
    }


    public int getLevel() {
        return level;
    }

    public int getPoints() {
        return points;
    }

    public void incPoints(int value) {
        this.points += value;
    }

    public char[][] getMaze(){
        return current_maze.getMaze();
    }

    public int getMazeRows(){
        return GameLevel.getLevelRows(this.stage);
    }

    public int getMazeCols(){
        return GameLevel.getLevelCols(this.stage);

    }


}
