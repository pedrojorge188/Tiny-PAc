package pt.isec.pa.tinypac.model.data.ghost;

import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.elements.Wall;
import pt.isec.pa.tinypac.model.data.pacman.PacMan;

import java.util.Random;

public class Pinky extends Ghost{

    private  Boolean m1,m2,m3;
    private Random rand;

    public Pinky(int x, int y) {
        super(x, y);
        this.name = "Pinky";
        m1 = true; m2 = true; m3 = true;
        rand = new Random();
    }

    @Override
    public void move(Maze maze, PacMan pacMan) {

        if(!vulnerability){
            if(getX() == pacMan.getX() && getY() == pacMan.getY())
                pacMan.removeLife();
        }

        if(m1) {

            if (maze.get(getY(), getX() + 1).getSymbol() != (new Wall()).getSymbol() && maze.get(getY(), getX() + 1).getSymbol() != 'y') {
                this.x++;

            }else if (maze.get(getY() - 1, getX()).getSymbol() != (new Wall()).getSymbol() && maze.get(getY() - 1, getX()).getSymbol() != 'y') {
                this.y--;


            } else if(maze.get(getY() + 1, getX()).getSymbol() != (new Wall()).getSymbol() && maze.get(getY() + 1, getX()).getSymbol() != 'y'){

                this.y ++;

            }else{
                m1 = false;
            }

        } else if( m2 ){

            if (maze.get(getY(), getX() + 1).getSymbol() != (new Wall()).getSymbol() && maze.get(getY(), getX() + 1).getSymbol() != 'y') {

                this.x++;

            } else if (maze.get(getY() + 1, getX()).getSymbol() != (new Wall()).getSymbol() && maze.get(getY() + 1, getX()).getSymbol() != 'y') {

                this.y++;

            }else {
                m2 = false;
            }

        } else if( m3 ){

            if (maze.get(getY(), getX() - 1).getSymbol() != (new Wall()).getSymbol() && maze.get(getY(), getX() - 1).getSymbol() != 'y') {

                this.x--;

            } else if (maze.get(getY() - 1, getX()).getSymbol() != (new Wall()).getSymbol() && maze.get(getY() - 1, getX()).getSymbol() != 'y') {

                this.y--;

            } else{
                m3 = false;
            }

        } else {

            if (maze.get(getY(), getX() - 1).getSymbol() != (new Wall()).getSymbol() && maze.get(getY(), getX() - 1).getSymbol() != 'y') {

                this.x--;


            } else if (maze.get(getY() + 1, getX()).getSymbol() != (new Wall()).getSymbol() && maze.get(getY() + 1, getX()).getSymbol() != 'y') {

                this.y++;

            } else{

                m1 = true;
                m2 = true;
                m3 = true;

            }

        }

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
