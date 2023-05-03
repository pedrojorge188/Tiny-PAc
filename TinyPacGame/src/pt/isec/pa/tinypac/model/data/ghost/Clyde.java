package pt.isec.pa.tinypac.model.data.ghost;

import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.elements.Wall;
import pt.isec.pa.tinypac.model.data.pacman.PacMan;

import java.util.Random;

public class Clyde extends Ghost{

    private Random rand = new Random();
    public Clyde(int x, int y) {
        super(x, y);
        this.name = "Clyde";
    }

    @Override
    public void move(Maze maze, PacMan pacMan) {

            if(!vulnerability){
                if(getX() == pacMan.getX() && getY() == pacMan.getY())
                    pacMan.removeLife();
            }

            if(pacMan.getY() == getY()){
                if(pacMan.getX() > getX()){
                        direction = RIGHT;

                }else if(pacMan.getX() < getX()){

                        direction = LEFT;
                }

            }else if(pacMan.getX() == getX()){

                if(pacMan.getY() < getY()){
                    direction = UP;

                }else if(pacMan.getY() > getY()){
                        direction = DOWN;
                }
            }


            if(direction == RIGHT){
                if(maze.get(getY(),getX()+1).getSymbol() != (new Wall()).getSymbol() && maze.get(getY(),getX()+1).getSymbol() != 'y'){
                    this.x ++;
                    return;
                }else{
                    int selectPositions = rand.nextInt(3);

                    if(selectPositions == 0){
                        direction = UP;

                    }else if(selectPositions == 1){
                        direction = DOWN;
                    }else{
                        direction = LEFT;
                    }
                }

            }

            if(direction == LEFT){
                if(maze.get(getY(),getX()-1).getSymbol() != (new Wall()).getSymbol() && maze.get(getY(),getX()-1).getSymbol() != 'y'){
                    this.x --;
                    return;
                }else{
                    int selectPositions = rand.nextInt(3);

                    if(selectPositions == 0){
                        direction = UP;

                    }else if(selectPositions == 1){
                        direction = DOWN;
                    }else{
                        direction = RIGHT;
                    }
                }

            }

            if(direction == UP){
                if(maze.get(getY()-1,getX()).getSymbol() != (new Wall()).getSymbol() && maze.get(getY()-1,getX()).getSymbol() != 'y'){
                    this.y --;
                    return;
                }else{
                    int selectPositions = rand.nextInt(3);

                    if(selectPositions == 0){
                        direction = RIGHT;

                    }else if(selectPositions == 1){
                        direction = LEFT;
                    }else{
                        direction = DOWN;
                    }
                }

            }

            if(direction == DOWN){
                if(maze.get(getY()+1,getX()).getSymbol() != (new Wall()).getSymbol() && maze.get(getY()+1,getX()).getSymbol() != 'y'){
                    this.y++;
                }else{
                    int selectPositions = rand.nextInt(3);

                    if(selectPositions == 0){
                        direction = RIGHT;

                    }else if(selectPositions == 1){
                        direction = LEFT;
                    }else{
                        direction = UP;
                    }
                }

            }


    }


    @Override
    public String toString() {
        return "Clyde{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                ", movement_speed=" + movement_speed +
                ", vulnerability_time=" + vulnerability_time +
                '}';
    }

    @Override
    public char getSymbol() {
        return 'C';
    }
}
