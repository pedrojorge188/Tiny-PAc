package pt.isec.pa.tinypac.model.data.ghost;

import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.elements.Wall;
import pt.isec.pa.tinypac.model.data.pacman.PacMan;

import java.util.Random;

public class Inky extends Ghost{

    private  Boolean m1,m2,m3;
    private Random rand;


    public Inky(int x, int y) {
        super(x, y);
        m1 = true; m2 = true; m3 = true;
        this.name = "Inky";
        rand = new Random();
    }

    private void move_directions(Maze maze){
        if (direction == RIGHT) {

            if(maze.get(getY(), getX() + 1).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() - 1, getX() + 1).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() - 1, getX()).getSymbol() == (new Wall()).getSymbol()){
                m1 = false;
            }

            if (maze.get(getY(), getX() + 1).getSymbol() != (new Wall()).getSymbol() && maze.get(getY(), getX() + 1).getSymbol() != 'y') {
                this.x++;
                return;
            } else {

                int selectPositions = rand.nextInt(3);

                if (selectPositions == 0) {
                    direction = UP;
                } else if (selectPositions == 1) {
                    direction = DOWN;
                } else {
                    direction = LEFT;
                }
            }

        }
        if (direction == LEFT) {
            if (maze.get(getY(), getX() - 1).getSymbol() != (new Wall()).getSymbol() && maze.get(getY(), getX() - 1).getSymbol() != 'y') {
                this.x--;
                return;
            } else {
                int selectPositions = rand.nextInt(3);

                if (selectPositions == 0) {
                    direction = UP;

                } else if (selectPositions == 1) {
                    direction = DOWN;
                } else {
                    direction = RIGHT;
                }
            }

        }
        if (direction == UP) {
            if (maze.get(getY() - 1, getX()).getSymbol() != (new Wall()).getSymbol() && maze.get(getY() - 1, getX()).getSymbol() != 'y') {
                this.y--;
                return;
            } else {
                int selectPositions = rand.nextInt(3);

                if (selectPositions == 0) {
                    direction = RIGHT;

                } else if (selectPositions == 1) {
                    direction = LEFT;
                } else {
                    direction = DOWN;
                }
            }

        }

        if (direction == DOWN) {
            if (maze.get(getY() + 1, getX()).getSymbol() != (new Wall()).getSymbol() && maze.get(getY() + 1, getX()).getSymbol() != 'y') {
                this.y++;
            } else {
                int selectPositions = rand.nextInt(3);

                if (selectPositions == 0) {
                    direction = RIGHT;

                } else if (selectPositions == 1) {
                    direction = LEFT;
                } else {
                    direction = UP;
                }
            }

        }
    }
    @Override
    public void move(Maze maze, PacMan pacMan) {

        if (!vulnerability) {
            if (getX() == pacMan.getX() && getY() == pacMan.getY())
                pacMan.removeLife();
        }

        if (m1) {

            if (maze.get(getY(), getX() + 1).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() + 1, getX() + 1).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() + 1, getX()).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() - 1, getX()).getSymbol() != (new Wall()).getSymbol()) {
                m1 = false;
            }

            move_directions(maze);

        } else if (m2) {

            if (maze.get(getY(), getX() - 1).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() + 1, getX() - 1).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() + 1, getX()).getSymbol() == (new Wall()).getSymbol()) {
                m2 = false;
            }


            move_directions(maze);

        } else if (m3) {

            if (maze.get(getY(), getX() + 1).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() - 1, getX() + 1).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() - 1, getX()).getSymbol() == (new Wall()).getSymbol()) {
                m3 = false;
            }


            move_directions(maze);

        } else if (m3) {

            if (maze.get(getY(), getX() - 1).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() - 1, getX() - 1).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() - 1, getX()).getSymbol() == (new Wall()).getSymbol()) {
                m1 = true;
                m2 = true;
                m3 = true;
            }


            move_directions(maze);

        }
    }

    @Override
    public String toString() {
        return "Inky{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                ", movement_speed=" + movement_speed +
                ", vulnerability_time=" + vulnerability_time +
                '}';
    }

}
