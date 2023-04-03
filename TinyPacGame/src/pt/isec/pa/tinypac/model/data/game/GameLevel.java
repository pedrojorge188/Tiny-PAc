package pt.isec.pa.tinypac.model.data.game;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.MazeElementsGenerator;
import pt.isec.pa.tinypac.model.data.game.interfaces.GameConsts;
import java.io.*;
import java.util.HashMap;

class GameLevel implements GameConsts {

    private static int n_wrap_zone = 0;
    private static int n_fruit = 0;
    private static int pacman_spawn = 0;
    private static int ghost_spawn = 0;


    public static class Result {
        private final Boolean value1;
        private final Maze value2;

        public Result(Boolean v1, Maze v2){
            value1 = v1;
            value2 = v2;
        }

        public Boolean getValue1() {
            return value1;
        }

        public Maze getValue2() {
            return value2;
        }
    }

    private static String verifyLevel(int level){

        StringBuilder file = new StringBuilder(FILE_DIRECTORY);

        if(level > 0 && level < MAX_LEVEL)
            if(level < 10)
                file.append("0").append(level).append(".txt");
            else
                file.append(+level).append(".txt");
        else
            return null;

        return file.toString();
    }

    private static Boolean verfifyElements(char ch){

        if (ch == WALL || ch == BALL || ch == WRAP_ZONE
                || ch == FRUIT || ch == SPAWN || ch == BIG_BALL || ch == SPAWN2
                || ch == CAVE) {
        }else{
            return false;
        }

        if(ch == WRAP_ZONE) {
            n_wrap_zone ++;
            if(n_wrap_zone > 2)
                return  false;
        }

        if(ch == FRUIT) {
            n_fruit ++;
            if(n_fruit > 1)
                return  false;
        }

        if(ch == SPAWN) {
            pacman_spawn ++;
            if(pacman_spawn > 1)
                return  false;
        }

        if(ch == SPAWN2) {
            ghost_spawn ++;
            if(ghost_spawn > 1)
                return  false;
        }

        return true;
    }

    public static Result getLevel(int level){

        Maze maze;
        String file = verifyLevel(level);

        if(file == null)
            return new Result(false,null);

        int rows = 0,cols = 0;

        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                rows++;
                cols = line.length();
            }

            reader.close();

            maze = new Maze(rows,cols);

            BufferedReader mazeRead = new BufferedReader(new FileReader(file));
            String get;

            int cLine = 0;
            while((get = mazeRead.readLine()) != null){
                for(int i=0;i < get.length(); i++){
                    maze.set(cLine,i, MazeElementsGenerator.create(get.charAt(i)));
                }
                cLine++;
            }

            char [][] m = maze.getMaze();

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if(!verfifyElements(m[i][j]))
                        return new Result(false,null);
                }
            }

            if(n_wrap_zone == 0 || n_fruit == 0 || pacman_spawn == 0 || ghost_spawn == 0)
                return new Result(false,null);

           n_wrap_zone = 0;
           n_fruit = 0;
           pacman_spawn = 0;
           ghost_spawn = 0;



        } catch (Exception e) {
            return new Result(false,null);
        }

        return new Result(true,maze);

    }

    public static int getLevelRows(int level){
        String file = verifyLevel(level);
        int rows = 0;

        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                rows++;
            }

            reader.close();

        } catch (IOException e) {
            return 0;
        }


        return rows;
    }

    public static int getLevelCols(int level){
        String file = verifyLevel(level);
        int cols = 0;

        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                cols = line.length();
            }

            reader.close();

        } catch (IOException e) {
            return 0;
        }


        return cols;
    }


    private GameLevel() {}

}
