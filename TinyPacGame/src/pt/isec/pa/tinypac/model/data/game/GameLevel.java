package pt.isec.pa.tinypac.model.data.game;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.data.MazeElementsGenerator;
import pt.isec.pa.tinypac.model.data.game.interfaces.GameConsts;
import java.io.*;

class GameLevel implements GameConsts {

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


    public static Maze getLevel(int level){

        Maze maze;
        String file = verifyLevel(level);
        if(file == null)
            return null;

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
                    if (m[i][j] == WALL || m[i][j] == BALL || m[i][j] == WRAP_ZONE
                            || m[i][j] == FRUIT || m[i][j] == SPAWN || m[i][j] == BIG_BALL || m[i][j] == SPAWN2
                            || m[i][j] == CAVE) {
                    }else{
                        return null;
                    }
                }
            }

        } catch (IOException e) {
            return null;
        }

        return maze;

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
