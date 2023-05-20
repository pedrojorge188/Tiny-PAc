package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.gameengine.IGameEngine;
import pt.isec.pa.tinypac.gameengine.IGameEngineEvolve;
import pt.isec.pa.tinypac.model.data.game.GameManager;
import pt.isec.pa.tinypac.model.data.ghost.Ghost;
import pt.isec.pa.tinypac.model.data.pacman.PacMan;
import pt.isec.pa.tinypac.model.fsm.TinyPacContext;
import pt.isec.pa.tinypac.model.fsm.TinyPacState;
import pt.isec.pa.tinypac.utils.Messages;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.HashSet;

/**
 * Manager do projeto
 */

public class Controller implements IGameEngineEvolve {

    private TinyPacContext fsm;
    private GameManager game;
    private PropertyChangeSupport pcs;
    private static int direction_manager;

    public static final String PROP_MAZE = "maze_property";
    public static final String PROP_GAME_INFO = "info_property";
    public static final String PROP_LOG = "logs_property";
    public static final String PROP_TOP5 = "leaderboard_property";

    public Controller() {

        game = new GameManager();
        this.fsm = new TinyPacContext(game);
        pcs = new PropertyChangeSupport(this);
    }


    public void addPropertyChangeListener(String proprety, PropertyChangeListener listener){
        pcs.addPropertyChangeListener(proprety, listener);
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

        pcs.firePropertyChange(PROP_MAZE,null,null);
        pcs.firePropertyChange(PROP_GAME_INFO,null,null);
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
            Messages.getInstance().addLog("GAME SAVED");

        }catch(Exception e){

            e.printStackTrace();
            return false;

        }finally {
            pcs.firePropertyChange(PROP_LOG,null,null);
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
            fsm.replaceGameManager(game);
            fsm.disableFsm();

            Messages.getInstance().clearLogs();
            Messages.getInstance().addLog("GAME RESTORE");

        }catch(Exception e){


            this.game = new GameManager();
            fsm.replaceGameManager(game);

            return false;

        }finally {
            pcs.firePropertyChange(PROP_MAZE,null,null);
            pcs.firePropertyChange(PROP_LOG,null,null);
            pcs.firePropertyChange(PROP_GAME_INFO, null , null);
        }

        fileO.delete();
        return true;
    }

    public boolean keyPress(int direction){

        pcs.firePropertyChange(PROP_LOG,null,null);
        direction_manager = direction;
        return fsm.keyPress(direction);
    }

    public int getPacDirection(){
        return direction_manager;
    }

    public boolean pause(){
        pcs.firePropertyChange(PROP_LOG,null,null);
        return fsm.pause();
    }

    public boolean resume(){
        pcs.firePropertyChange(PROP_LOG,null,null);
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

    public PacMan getPacmanCPY(){
        return fsm.getPacman();
    }
    
    public HashSet<Ghost> getGhostCPY(){
        return fsm.getGhosts();
    }

    public int getLevel(){
        return fsm.getLevel();
    }

    public int getPoints(){
       return fsm.getPoints();
    }

    public void disableGameRoles(){

        game = new GameManager();
        fsm.replaceGameManager(game);
        fsm.disableFsm();

    }

}
