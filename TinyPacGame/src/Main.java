import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import pt.isec.pa.tinypac.model.data.game.GameManager;

public class Main {

    public static void main(String[] args) {

        GameManager gm = new GameManager();

        try {
            char [][] m = gm.getMaze();
            for (int i = 0; i < gm.getMazeRows(); i++) {
                for (int j = 0; j < gm.getMazeCols(); j++) {
                    System.out.print(m[i][j]);
                }
                System.out.println();
            }
        }catch (Exception e){
            System.out.println("Mapa do nivel "+gm.getLevel()+" está mal estruturado!");
        }

    }

}