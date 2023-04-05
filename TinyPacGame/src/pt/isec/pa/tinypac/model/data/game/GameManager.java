package pt.isec.pa.tinypac.model.data.game;

import pt.isec.pa.tinypac.model.IConst;
import pt.isec.pa.tinypac.model.data.Balls.Ball;
import pt.isec.pa.tinypac.model.data.Balls.BigBall;
import pt.isec.pa.tinypac.model.data.Fruit.Fruit;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.elements.*;
import pt.isec.pa.tinypac.model.data.elements.Void;
import pt.isec.pa.tinypac.model.data.game.interfaces.GameConsts;
import pt.isec.pa.tinypac.model.data.ghost.*;
import pt.isec.pa.tinypac.model.data.pacman.PacMan;

import java.util.HashSet;
import java.util.Set;

public class GameManager implements GameConsts , IConst {

    private static boolean level_trigger;
    private int level;
    private int stage;
    private int points;
    private Maze current_maze;
    private Set<Ghost> ghost_list;
    private PacMan pacman;
    private Fruit fruit;
    private Set <Ball> balls_list;
    private Set <WrapZone> portals;

    public GameManager(){

        this.level_trigger = false;
        this.level = 1;
        this.stage = 1;
        this.points = 0;
        this.current_maze = GameLevel.getLevel(this.stage).getValue2();
        ghost_list = new HashSet<>();
        balls_list = new HashSet<>();
        portals = new HashSet<>();
        pacman = null;
        fruit = null;

    }

    public void moveGhost(){
        Void void_element = new Void();
        GhostSpawn ghost = new GhostSpawn();

        for (Ghost e: ghost_list){
            current_maze.set(e.getY(), e.getX(), void_element);
            e.move(current_maze,pacman);
            current_maze.set(e.getY(),e.getX(), void_element);
        }
    }

    public void movePacman(int direction) {

        BallElement b = new BallElement();
        Void void_element = new Void();
        WrapZone wrapzone = new WrapZone(0,0);
        PacManSpawn nPac = new PacManSpawn();
        Wall w = new Wall();

        int x = 0, y = 0;

        current_maze.set(pacman.getY(), pacman.getX(), void_element);

        for(WrapZone e : portals)
            current_maze.set(e.getY(), e.getX(), wrapzone);

        switch (direction){

            case UP:
                if(current_maze.get(pacman.getY()-1,pacman.getX()).getSymbol()!= w.getSymbol()) {
                    if(current_maze.get(pacman.getY()-1,pacman.getX()).getSymbol() == wrapzone.getSymbol()){
                        for(WrapZone e:portals)
                            if(e.getX() == pacman.getX() && e.getY() == pacman.getY()-1)
                                wrapzone = e;
                        for(WrapZone e : portals)
                            if(e.getId() != wrapzone.getId()){
                                pacman.setX(e.getX());
                                pacman.setY(e.getY());
                            }

                    }else{
                        pacman.moveUp();
                    }
                }
                break;

            case LEFT:
                if(current_maze.get(pacman.getY(),pacman.getX()-1).getSymbol() != w.getSymbol()) {
                    if(current_maze.get(pacman.getY(),pacman.getX()-1).getSymbol() == wrapzone.getSymbol()){
                        for(WrapZone e:portals)
                            if(e.getX() == pacman.getX() -1&& e.getY() == pacman.getY())
                                wrapzone = e;
                        for(WrapZone e : portals)
                            if(e.getId() != wrapzone.getId()){
                                pacman.setX(e.getX());
                                pacman.setY(e.getY());
                            }

                    }else{
                        pacman.moveLeft();
                    }
                }
                break;

            case DOWN:
                if(current_maze.get(pacman.getY()+1,pacman.getX()).getSymbol() != w.getSymbol()){
                    if(current_maze.get(pacman.getY()+1,pacman.getX()).getSymbol() == wrapzone.getSymbol()){
                        for(WrapZone e:portals)
                            if(e.getX() == pacman.getX() && e.getY() == pacman.getY()+1)
                                wrapzone = e;
                        for(WrapZone e : portals)
                            if(e.getId() != wrapzone.getId()){
                                pacman.setX(e.getX());
                                pacman.setY(e.getY());
                            }

                    }else{
                        pacman.moveDown();
                    }

                }

                break;

            case RIGHT:
                if(current_maze.get(pacman.getY(),pacman.getX()+1).getSymbol() != w.getSymbol()){
                    if(current_maze.get(pacman.getY(),pacman.getX()+1).getSymbol() == wrapzone.getSymbol()){
                        for(WrapZone e:portals)
                            if(e.getX() == pacman.getX()+1 && e.getY() == pacman.getY())
                                wrapzone = e;
                        for(WrapZone e : portals)
                            if(e.getId() != wrapzone.getId()){
                                pacman.setX(e.getX());
                                pacman.setY(e.getY());
                            }

                    }else{
                        pacman.moveRight();
                    }
                }

                break;
        }

        current_maze.set(pacman.getY(), pacman.getX(), nPac);

        for(Ball e:balls_list){
            if (e.getX() == pacman.getX() && e.getY() == pacman.getY()){
                this.points += e.getPoints();
                balls_list.remove(e);
                break;
            }
        }
    }

    public boolean fillGame(){

        ghost_list.clear();
        balls_list.clear();
        portals.clear();

        char [][] tmp = current_maze.getMaze();

        for (int i = 0; i < this.getMazeRows(); i++) {
            for (int j = 0; j < this.getMazeCols(); j++) {
                if(tmp[i][j] == SPAWN2){

                    ghost_list.add(new Inky(j,i));
                    ghost_list.add(new Pinky(j,i));
                    ghost_list.add(new Blinky(j,i));
                    ghost_list.add(new Clyde(j,i));

                    for(Ghost e : ghost_list) {
                        e.setMovement_speed(this.level *1);
                        e.setVulnerability_time(e.getVulnerability_time()-this.level);
                    }

                }else if(tmp[i][j] == SPAWN){
                    if(!level_trigger){
                        pacman = new PacMan(j,i);
                    }else{
                        pacman.setX(j);
                        pacman.setY(i);
                    }

                }else if(tmp[i][j] == BALL){
                    balls_list.add(new Ball(j,i));
                }else if(tmp[i][j] == BIG_BALL){
                    balls_list.add(new BigBall(j,i));
                }else if(tmp[i][j] == FRUIT){
                    fruit = new Fruit(j,i);
                }else if(tmp[i][j] == WRAP_ZONE){
                    portals.add(new WrapZone(j,i));
                }
            }

        }
        return true;
    }

    public boolean setLevel(int level) {

        if(level > 20 || level < 0)
            return false;

        this.level = level;
        this.level_trigger = true;

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

    public int getPacManLife(){
        return pacman.getLives();
    }

    public void removePacManLife(){
        pacman.removeLife();
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