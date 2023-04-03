package pt.isec.pa.tinypac.model.data.game;
import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Maze;

public class GameManager{

    private int level;
    private int points;
    private Maze current_maze;

    public GameManager(){
        this.level = 1;
        this.points = 0;
        this.current_maze = GameLevel.getLevel(this.level).getValue2();
    }

    public int getLevel() {
        return level;
    }

    public boolean setLevel(int level) {

        if(level < 20 && level > 0){

            if(GameLevel.getLevel(level).getValue1()){
                current_maze = GameLevel.getLevel(level).getValue2();
                this.level = level;
            }else{
                return false;
            }

        } else {
            return false;
        }

        return true;
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
        return GameLevel.getLevelRows(this.level);
    }

    public int getMazeCols(){
        return GameLevel.getLevelCols(this.level);
    }

}
