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

    private boolean move_directions(Maze maze){
        if (direction == RIGHT) {

            if(maze.get(getY(), getX() + 1).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() - 1, getX() + 1).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() - 1, getX()).getSymbol() == (new Wall()).getSymbol()){
                m1 = false;
            }

            if (maze.get(getY(), getX() + 1).getSymbol() != (new Wall()).getSymbol() && maze.get(getY(), getX() + 1).getSymbol() != 'y') {
                this.x++;
                moves.add(RIGHT);
                return true;
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
                moves.add(LEFT);
                return true;
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
                moves.add(UP);
                return true;
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
                moves.add(DOWN);
                return true;
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

        return false;

    }
    @Override
    public void move(Maze maze, PacMan pacMan) {

        if(returning_moves()){
            return;
        }

        if(!vulnerability){
            if(getX() == pacMan.getX() && getY() == pacMan.getY())
                pacMan.removeLife();
        }

        if(m1) {

            if(maze.get(getY(), getX() + 1).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() - 1, getX() + 1).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() - 1, getX()).getSymbol() == (new Wall()).getSymbol()){
                m1 = false;
            }

            if(move_directions(maze))
                return;

        }else if(m2){

            if(maze.get(getY(), getX() + 1).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() + 1, getX() + 1).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() + 1, getX()).getSymbol() == (new Wall()).getSymbol()){
                m2 = false;
            }


            if(move_directions(maze))
                return;

        }else if(m3){

            if(maze.get(getY(), getX() - 1).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() - 1, getX() - 1).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() - 1, getX()).getSymbol() == (new Wall()).getSymbol()){
                m3 = false;
            }


            if(move_directions(maze))
                return;

        }else if(m3){

            if(maze.get(getY(), getX() - 1).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() + 1, getX() - 1).getSymbol() == (new Wall()).getSymbol() &&
                    maze.get(getY() + 1, getX()).getSymbol() == (new Wall()).getSymbol()){
                m1 = true; m2 = true; m3 = true;
            }


            if(move_directions(maze))
                return;

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

    @Override
    public char getSymbol() {
        return 'K';
    }
}
