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
    static int eat_to_fruit = 0;

    private int level;
    private int stage;
    private int points;
    private Maze current_maze;
    private Set<Ghost> ghost_list;
    private PacMan pacman;
    private Fruit fruit;
    private int total_foods;
    private int eaten_foods;
    private Set <Ball> balls_list;
    private Set <WrapZone> portals;

    public GameManager(){
        this.total_foods = 0;
        this.eaten_foods = 0;
        this.level = 1;
        this.stage = 1;
        this.points = 0;
        this.current_maze = GameLevel.getLevel(this.stage).getValue2();
        ghost_list = new HashSet<>();
        balls_list = new HashSet<>();
        portals = new HashSet<>();
        pacman = new PacMan(0,0);
        fruit = new Fruit(0,0);

    }

    public GameManager(GameManager g){
        this.total_foods = g.total_foods;
        this.eaten_foods = g.eaten_foods;
        this.level = g.level;
        this.stage = g.stage;
        this.points = g.getPoints();
        this.current_maze = g.current_maze;
        ghost_list = new HashSet<>(g.ghost_list);
        balls_list = new HashSet<>(g.balls_list);
        portals = new HashSet<>(g.portals);
        pacman = new PacMan(g.pacman);
        fruit = g.fruit;

    }

    public void moveGhost(){

        for (Ghost e: ghost_list){
            e.move(current_maze,pacman);
        }
    }

    public HashSet<Ghost> getGhost(){
        HashSet<Ghost> cpy = new HashSet<>(ghost_list) ;
        return cpy;
    }

    public PacMan getPacman(){
        PacMan cpy = new PacMan(pacman);
        return cpy;
    }

    public int getBuff(){

        int result = 0;

        for(Ball e : balls_list){
            if(e.getPoints() > 1){
                result++;
            }
        }

        return result;

    }

    public void setPoints(int value){
        this.points += value;
    }

    public void toogleGhostsStatus(boolean value){
        for (Ghost e: ghost_list){
                 e.toggleVulnerability(value);
        }
    }

    public boolean movePacman(int direction) {

        BallElement b = new BallElement();
        Void void_element = new Void();
        WrapZone wrapzone = new WrapZone(0,0);
        Wall w = new Wall();

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
                }else{
                    return false;
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
                }else{
                    return false;
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

                }else{
                    return false;
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
                }else{
                    return false;
                }

                break;
        }


        for(Ball e:balls_list){
            if (e.getX() == pacman.getX() && e.getY() == pacman.getY()){

                if(eat_to_fruit == FRUIT_SPAWN){
                    if(!fruit.getStatus()){
                        fruit.setActive(true);
                        eat_to_fruit = 0;
                    }
                    eat_to_fruit = 0;
                }else{
                    eat_to_fruit++;
                }

                current_maze.set(e.getY(),e.getX(),void_element);
                this.points += e.getPoints();
                this.eaten_foods++;
                balls_list.remove(e);
                break;
            }


        }

        if(pacman.getX() == fruit.getX() && pacman.getY() == fruit.getY() && fruit.getStatus()){
            points += fruit.getPoints();
            fruit.setActive(false);
        }

        return true;
    }

    public boolean fillGame(){

        int counter = 0;
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
                    PacMan p = new PacMan(this.pacman);
                    pacman = new PacMan(j,i);

                    pacman.setLives(p.getLives());

                }else if(tmp[i][j] == BALL){
                    counter ++;
                    balls_list.add(new Ball(j,i));
                }else if(tmp[i][j] == BIG_BALL){
                    counter ++;
                    balls_list.add(new BigBall(j,i));
                }else if(tmp[i][j] == FRUIT){
                    fruit = new Fruit(fruit,j,i);
                }else if(tmp[i][j] == WRAP_ZONE){
                    portals.add(new WrapZone(j,i));
                }
            }

        }

        if(total_foods == 0)
            total_foods = counter;

        return true;

    }

    public boolean setLevel() {

        if(level > 20 || level < 0)
            return false;

        this.eaten_foods = 0;
        this.total_foods = 0;
        this.level += 1;

        if(!GameLevel.getLevel(level).getValue1()){
            current_maze = GameLevel.getLevel(this.stage).getValue2();
            return true;
        }

        this.stage += 1;
        current_maze = GameLevel.getLevel(this.level).getValue2();

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

    public boolean stepLevelState(){
        if(eaten_foods == total_foods)
            return true;
        else
            return false;
    }

    public boolean getFruitStatus(){
        return fruit.getStatus();
    }

}