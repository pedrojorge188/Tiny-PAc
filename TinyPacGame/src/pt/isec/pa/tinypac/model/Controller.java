package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.utils.Messages;

import java.io.*;

public class Controller implements IGameEngineEvolve {

    private TinyPacContext fsm;
    private GameManager game;

    public Controller() {

        game = new GameManager();
        this.fsm = new TinyPacContext(game);
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        fsm.action();
    }

    public boolean verifyGameRestore(){
        File fileO = new File("files/save.dat");
        try(FileInputStream file = new FileInputStream(fileO);
            ObjectInputStream ois = new ObjectInputStream(file);){

            return true;

        }catch(Exception e){

            return false;
        }


    }

    public boolean saveGame(){

        try(FileOutputStream file = new FileOutputStream("files/save.dat");
            ObjectOutputStream oos = new ObjectOutputStream(file);){

            oos.writeObject(game);
            Messages.getInstance().clearLogs();
            Messages.getInstance().addLog("jogo salvado");

        }catch(Exception e){

            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean deleteCacheFiles(){

        File fileO = new File("files/save.dat");
        try(FileInputStream file = new FileInputStream(fileO);
            ObjectInputStream ois = new ObjectInputStream(file);){


        }catch(Exception e){

            return false;
        }

        fileO.delete();

        return true;

    }

    public boolean restoreGame(){
        File fileO = new File("files/save.dat");
        try(FileInputStream file = new FileInputStream(fileO);
            ObjectInputStream ois = new ObjectInputStream(file);){

            game = (GameManager) ois.readObject();

            //para remover
            System.out.println("level -> " + game.getLevel());
            System.out.println("points -> " + game.getPoints());

            Messages.getInstance().clearLogs();
            Messages.getInstance().addLog("jogo carregado");

        }catch(Exception e){
            Messages.getInstance().clearLogs();
            Messages.getInstance().addLog("Nenhum jogo para carregar");
            this.game = new GameManager();
            return false;
        }

        fileO.delete();
        return true;
    }

    public boolean keyPress(int direction){
        return fsm.keyPress(direction);
    }

    public boolean pause(){
        return fsm.pause();
    }

    public boolean resume(){
        return  fsm.resume();
    }

    public TinyPacState getState(){
        return fsm.getState();
    }

    public int getPacmanLife(){
        return fsm.getPacManLife();
    }

    public int getGameRows(){
        return fsm.getMazeRows();
    }

    public int getGameCols(){
        return fsm.getMazeCols();
    }

    public boolean getFruit(){
        return fsm.getFruit();
    }

    public char[][] getMaze(){
        return fsm.getMaze();
    }

    public int getLevel(){
        return fsm.getLevel();
    }

    public int getPoints(){
        return fsm.getPoints();
    }
}
